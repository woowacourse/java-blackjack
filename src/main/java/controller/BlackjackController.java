package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();

        play(blackjackGame);
        printResult(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        BlackjackGame blackjackGame = new BlackjackGame(createPlayers());
        blackjackGame.giveInitCards();
        outputView.printInitCards(blackjackGame.getDealer(), blackjackGame.getPlayers());
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

    private void play(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            requestMoreCard(blackjackGame, player);
        }

        blackjackGame.giveAdditionalCardToDealer();
        outputView.printDealerHitCount(blackjackGame.getDealerHitCardCount());
    }

    private void requestMoreCard(BlackjackGame blackjackGame, Player player) {
        while (!player.isBusted() && isHitCommand(player.getName())) {
            blackjackGame.giveCardTo(player);
            outputView.printPlayerCards(player);
        }

        printPlayerCurrentState(player);
    }

    private boolean isHitCommand(String name) {
        Command command = retryOnInvalidUserInput(
                () -> Command.from(inputView.requestMoreCard(name))
        );

        return command == Command.HIT;
    }

    private void printPlayerCurrentState(Player player) {
        if (player.isBusted()) {
            outputView.printBusted(player.getName());
            return;
        }

        outputView.printPlayerCards(player);
    }

    private void printResult(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();

        blackjackGame.decideResult();
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
