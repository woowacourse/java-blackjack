package controller;

import domain.BlackjackGame;
import domain.Command;
import domain.Dealer;
import domain.Player;
import domain.PlayerNames;
import domain.Players;
import java.util.List;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();

        playPlayersTurn(blackjackGame);
        playDealerTurn(blackjackGame);

        printResults(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        BlackjackGame blackjackGame = new BlackjackGame(createPlayers());
        blackjackGame.handOutInitialCards();
        outputView.printInitialCards(blackjackGame.getDealer(), blackjackGame.getPlayers());
        return blackjackGame;
    }

    private Players createPlayers() {
        return retryOnInvalidUserInput(
                () -> Players.from(PlayerNames.from(readNames()))
        );
    }

    private List<String> readNames() {
        return inputView.requestPlayerNames();
    }

    private void playPlayersTurn(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            playPlayerTurn(blackjackGame, player);
        }
    }

    private void playPlayerTurn(BlackjackGame blackjackGame, Player player) {
        while (!player.isBusted() && requestMoreCardTo(player) == Command.HIT) {
            blackjackGame.handOutCardTo(player);
            outputView.printPlayerCards(player);
        }

        printPlayerCurrentState(player);
    }

    private Command requestMoreCardTo(Player player) {
        String playerName = player.getName();
        return retryOnInvalidUserInput(
                () -> Command.from(inputView.requestMoreCard(playerName))
        );
    }

    private void playDealerTurn(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        blackjackGame.handOutAdditionalCardToDealer();
        outputView.printDealerHitCount(dealer.getHitCardCount());
    }

    private void printPlayerCurrentState(Player player) {
        if (player.isBusted()) {
            outputView.printBusted(player.getName());
            return;
        }

        outputView.printPlayerCards(player);
    }

    private void printResults(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();

        blackjackGame.result();
        Dealer dealer = blackjackGame.getDealer();
        outputView.printCardsWithScore(dealer, players);
        outputView.printFinalResult(dealer);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }
}
