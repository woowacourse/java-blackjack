package controller;

import model.BlackjackGame;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import model.paticipant.Dealer;
import model.paticipant.Player;
import model.paticipant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final BlackjackGame blackjackGame;
    private final GameMode gameMode;

    public BlackjackController(BlackjackGame blackjackGame, GameMode gameMode) {
        this.blackjackGame = blackjackGame;
        this.gameMode = gameMode;
    }

    public void run() {
        Dealer dealer = new Dealer();
        Players players = gameMode.createPlayers();

        initialDeal(dealer, players);
        proceedTurns(dealer, players);
        finishGame(dealer, players);
    }

    private void initialDeal(Dealer dealer, Players players) {
        blackjackGame.drawInitCards(dealer, players);
        OutputView.printCardOpen(dealer, players.players());
    }

    private void proceedTurns(Dealer dealer, Players players) {
        players.forEach(this::processPlayerTurn);

        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer);
            blackjackGame.drawOneCard(dealer);
        }
    }

    private void processPlayerTurn(Player player) {
        if (player.isBlackjack()) {
            return;
        }

        boolean hasDrawn = hitUntilStand(player);
        if (!hasDrawn) {
            OutputView.printCardByPlayer(player);
        }
    }

    private boolean hitUntilStand(Player player) {
        boolean hasDrawn = false;
        while (canHitMore(player)) {
            blackjackGame.drawOneCard(player);
            OutputView.printCardByPlayer(player);
            hasDrawn = true;
        }
        return hasDrawn;
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player);
        return Continuation.from(inputCommand);
    }

    private void finishGame(Dealer dealer, Players players) {
        OutputView.printFinalCards(dealer, players.players());
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, players);
        gameMode.reportResult(playerResult);
    }
}
