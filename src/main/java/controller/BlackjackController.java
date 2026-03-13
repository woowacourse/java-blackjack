package controller;

import model.BlackjackService;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import model.paticipant.Dealer;
import model.paticipant.Player;
import model.paticipant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final BlackjackService blackjackService;
    private final GameMode gameMode;

    public BlackjackController(BlackjackService blackjackService, GameMode gameMode) {
        this.blackjackService = blackjackService;
        this.gameMode = gameMode;
    }

    public void run() {
        Dealer dealer = new Dealer();
        Players players = gameMode.createPlayers();

        drawInitCards(dealer, players);
        drawMoreCardByPlayer(dealer, players);

        printFinalCards(dealer, players);
        reportResult(dealer, players);
    }

    private void drawMoreCardByPlayer(Dealer dealer, Players players) {
        players.forEach(this::chooseHitOrStand);
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer);
            blackjackService.drawOneCard(dealer);
        }
    }

    private void drawInitCards(Dealer dealer, Players players) {
        blackjackService.drawTwoCards(dealer, players);
        OutputView.printCardOpen(players);
        OutputView.printCardByDealer(dealer);
        OutputView.printCardByPlayers(players);
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player);
        return Continuation.from(inputCommand);
    }

    private void chooseHitOrStand(Player player) {
        boolean didDraw = drawMoreCard(player);
        if (!didDraw) {
            OutputView.printCardByPlayer(player);
        }
    }

    private boolean drawMoreCard(Player player) {
        boolean didDraw = false;
        while (canHitMore(player)) {
            blackjackService.drawOneCard(player);
            OutputView.printCardByPlayer(player);
            didDraw = true;
        }
        return didDraw;
    }

    private void printFinalCards(Dealer dealer, Players players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        players.forEach(OutputView::printCardByPlayerWithScore);
    }

    private void reportResult(Dealer dealer, Players players) {
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, players);
        gameMode.reportResult(playerResult);
    }
}
