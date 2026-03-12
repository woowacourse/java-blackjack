package controller;

import domain.participant.player.Player;
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
        ResultView.printStartPlayersCards(blackjackService.getDealerInfo(), playerManager.getAllPlayersInfo());

        participantsHit();

        finalizeGameResult();

        ResultView.printCardsAndScoreResult(blackjackService.getDealerInfo(), playerManager.getAllPlayersInfo());
        ResultView.printRankResult(blackjackService.getDealerInfo(), playerManager.getAllPlayersInfo());
    }

    private void gameSetup() {
        List<String> names = InputView.askName();
        playerManager.registerPlayers(names);

        blackjackService.dealDealerCardsOut();

        for (Player player : playerManager.getPlayers()) {
            blackjackService.dealPlayerCardsOut(player);
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
            ResultView.printPlayerCards(player.getName(), player.getCards());
        }
    }

    private void finalizeGameResult() {
        for (Player player : playerManager.getPlayers()) {
            blackjackService.finalizeGameResult(player);
        }
    }

    private boolean canHitAndDraw(Player player) {
        if (player.isBust()) {
            return false;
        }

        if (InputView.askHit(player.getName())) {
            blackjackService.playerHit(player);
            return true;
        }

        ResultView.printPlayerCards(player.getName(), player.getCards());
        return false;
    }
}
