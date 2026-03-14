package controller;

import domain.participant.player.Player;
import domain.vo.Money;
import service.BlackjackService;
import service.PlayerManager;
import view.InputView;
import view.ResultView;

import java.util.List;

public class GameController {
    private final BlackjackService blackjackService;
    private final PlayerManager playerManager;

    public GameController(BlackjackService blackjackService, PlayerManager playerManager) {
        this.blackjackService = blackjackService;
        this.playerManager = playerManager;
    }

    public void run() {
        gameSetup();
        askBettingMoney();

        participantsHit();
        finalizeGameResult();

        printProfits();
    }

    private void gameSetup() {
        List<String> names = InputView.askName();
        playerManager.registerPlayers(names);

        blackjackService.dealDealerCardsOut();

        for (Player player : playerManager.getPlayers()) {
            blackjackService.dealPlayerCardsOut(player);
        }

        ResultView.printStartPlayersCards(blackjackService.getDealerInfo(), playerManager.getAllPlayersInfo());
    }

    private void askBettingMoney() {
        for (Player player : playerManager.getPlayers()) {
            blackjackService.placeBet(player, new Money(InputView.askBettingMoney(player.getName().getValueOf())));
        }
    }

    private void participantsHit() {
        for (Player player : playerManager.getPlayers()) {
            playerHit(player);
        }

        if (blackjackService.isDealerHit()) {
            ResultView.printDealerOneMoreCard();
        }
    }

    private void playerHit(Player player) {
        while (canHitAndDraw(player)) {
            ResultView.printPlayerCards(player.getName().getValueOf(), player.getCards());
        }
    }

    private void finalizeGameResult() {
        for (Player player : playerManager.getPlayers()) {
            blackjackService.finalizeGameResult(player);
        }

        ResultView.printCardsAndScoreResult(blackjackService.getDealerInfo(), playerManager.getAllPlayersInfo());
        ResultView.printRankResult(blackjackService.getDealerInfo(), playerManager.getAllPlayersInfo());
    }

    private boolean canHitAndDraw(Player player) {
        if (player.isBust()) {
            return false;
        }

        if (InputView.askHit(player.getName().getValueOf())) {
            blackjackService.playerHit(player);
            return true;
        }

        ResultView.printPlayerCards(player.getName().getValueOf(), player.getCards());
        return false;
    }

    private void printProfits() {
        ResultView.printDealerProfit(blackjackService.getDealerProfit());

        for (Player player : playerManager.getPlayers()) {
            ResultView.printPlayerProfit(player.getName(), blackjackService.getProfit(player))
            ;
        }
    }
}
