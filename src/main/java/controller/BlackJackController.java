package controller;

import domain.DeckGenerator;
import domain.Game;
import domain.Player;
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
        retryUntilSuccess(() -> giveAdditionalCards(game));
        giveAdditionalCardsForDealer(game);
        displayScores(game);
        displayGameResult(game);
    }

    public Game startGame() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Game(playerNames, DeckGenerator.generateRandomDeck());
    }

    private void giveAdditionalCards(Game game) {
        game.getPlayers()
                .forEach((player) -> hitOrStay(game, player));
    }

    private void giveAdditionalCardsForDealer(Game game) {
        outputView.displayEmptyLine();
        while (game.doesDealerNeedCard()) {
            game.hitDealerCard();
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

    private void hitOrStay(Game game, Player player) {
        Answer answer = Answer.YES;
        while (!player.isBlackJack() && !player.isBust() && answer.isYes()) {
            answer = inputView.readHitOrStay(player);
            playerHitByAnswer(game, player, answer);
            outputView.displayParticipantAndCards(player);
            outputView.displayEmptyLine();
        }
    }

    private void playerHitByAnswer(Game game, Player player, Answer answer) {
        if (answer.isYes()) {
            game.hitPlayerCard(player);
        }
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
