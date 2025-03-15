package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.game.Game;
import domain.game.GameResult;
import domain.shuffler.RandomShuffler;
import java.util.EnumMap;
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
        giveAdditionalCardsForPlayer(game);
        giveAdditionalCardsForDealer(game);
        displayScores(game);
        calculateAndDisplayGameResult(game);
    }

    public Game startGame() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Game(playerNames, new Deck(new RandomShuffler()));
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

    private void calculateAndDisplayGameResult(Game game) {
        EnumMap<GameResult, Integer> gameResults = game.getDealer().calculateGameResult(game.getPlayers());
        outputView.displayGameResult(game, gameResults);
    }

    private void hitOrStay(Game game, String playerName) {
        Answer answer = Answer.YES;
        while (game.canHit(playerName) && answer == Answer.YES) {
            answer = retryUntilSuccess(() -> inputView.readHitOrStay(playerName));
            hitByAnswer(game, playerName, answer);
            displayPlayerCards(game, playerName);
        }
    }

    private void hitByAnswer(Game game, String playerName, Answer answer) {
        if (answer == Answer.YES) {
            game.playerHit(playerName);
        }
    }

    private void displayPlayerCards(Game game, String playerName) {
        List<Card> cards = game.getPlayerCards(playerName);
        outputView.displayNameAndCards(playerName, cards);
        outputView.displayEmptyLine();
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.printf("%s%n", e.getMessage());
            return retryUntilSuccess(supplier);
        }
    }
}
