package controller;

import domain.BlackjackGame;
import domain.participant.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        try {
            BlackjackGame blackjackGame = BlackjackGame.of(getPlayersName());
            initBetting(blackjackGame);

            initParticipantsHand(blackjackGame);
            runPlayersTurn(blackjackGame);
            runDealerTurn(blackjackGame);

            OutputView.printAllHands(blackjackGame.getDealer(), blackjackGame.getPlayers());
            OutputView.printBettingResult(blackjackGame.getBettingResult());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private List<String> getPlayersName() {
        try {
            return InputView.readPlayersName();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return getPlayersName();
        }
    }

    private void initBetting(BlackjackGame blackjackGame) {
        List<Player> players = blackjackGame.getPlayers();
        for (Player player : players) {
            betEachPlayer(blackjackGame, player);
        }
    }

    private void betEachPlayer(BlackjackGame blackjackGame, Player player) {
        try {
            player.betPlayer(InputView.readBetMoney(player));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            betEachPlayer(blackjackGame, player);
        }
    }

    private void initParticipantsHand(BlackjackGame blackjackGame) {
        OutputView.printStartMessage(blackjackGame.getPlayersName());

        blackjackGame.start();

        OutputView.printDealerCard(blackjackGame.getDealer());
        OutputView.printPlayersCard(blackjackGame.getPlayers());
    }

    public void runPlayersTurn(BlackjackGame blackjackGame) {
        List<Player> players = blackjackGame.getPlayers();
        for (Player player : players) {
            runPlayerTurn(blackjackGame, player);
        }
    }

    private void runPlayerTurn(BlackjackGame blackjackGame, Player player) {
        while (!player.isBust() && isCommandHit(player)) {
            blackjackGame.giveCardToParticipant(player);
            OutputView.printPlayerCard(player);
        }
    }

    private boolean isCommandHit(Player player) {
        try {
            String targetCommand = InputView.readHit(player);
            return HitCommand.HIT == HitCommand.find(targetCommand);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return isCommandHit(player);
        }
    }

    private void runDealerTurn(BlackjackGame blackjackGame) {
        if (blackjackGame.canDealerHit()) {
            blackjackGame.playDealerTurn();
            OutputView.printDealerHit();
        }
    }
}
