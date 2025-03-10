package controller;

import domain.Card;
import domain.Deck;
import domain.Game;
import java.util.List;
import java.util.function.Supplier;
import view.Answer;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Game game = retryUntilSuccess(this::startGame);
        outputView.displayInitialDeal(game);
        retryUntilSuccess(() -> giveAdditionalCardsForPlayer(game));
        giveAdditionalCardsForDealer(game);
        displayScores(game);
        displayGameResult(game);
    }

    public Game startGame() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Game(playerNames, new Deck());
    }

    private void giveAdditionalCardsForPlayer(Game game) {
        game.getPlayerNames()
                .forEach((name) -> hitOrStay(game, name));
    }

    private void giveAdditionalCardsForDealer(Game game) {
        outputView.displayEmptyLine();
        while (game.doesDealerNeedCard()) {
            game.dealerHit();
            outputView.displayDealerHitResult();
        }
        outputView.displayEmptyLine();
    }

    private void displayScores(Game game) {
        outputView.displayScore(game);
    }

    private void displayGameResult(Game game) {
        game.calculateGameResult();
        outputView.displayGameResult(game);
    }

    private void hitOrStay(Game game, String playerName) {
        Answer answer = inputView.readHitOrStay(playerName);
        while (game.canHit(playerName) && answer == Answer.YES) {
            game.playerHit(playerName);
            displayPlayerCards(game, playerName);
            answer = inputView.readHitOrStay(playerName);
        }
        if (answer == Answer.NO) {
            displayPlayerCards(game, playerName);
        }
    }

    private void displayPlayerCards(Game game, String playerName) {
        List<Card> cards = game.getPlayerCards(playerName);
        outputView.displayNameAndCards(playerName, cards);
        outputView.displayEmptyLine();
    }

    private void retryUntilSuccess(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            System.out.printf("%s%n", e.getMessage());
            retryUntilSuccess(runnable);
        }
    }

    private Game retryUntilSuccess(Supplier<Game> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.printf("%s%n", e.getMessage());
            return retryUntilSuccess(supplier);
        }
    }
}
