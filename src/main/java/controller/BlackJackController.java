package controller;

import domain.Deck;
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
    private final Deck deck = new Deck();

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
        return new Game(playerNames, deck);
    }

    private void giveAdditionalCards(Game game) {
        game.getPlayers()
                .forEach((player) -> hitOrStay(game, player));
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

    private void hitOrStay(Game game, Player player) {
        Answer answer;
        do {
            if (player.isBlackJack() || player.isBust()) {
                return;
            }
            answer = inputView.readHitOrStay(player);
            if (answer == Answer.YES) {
                game.hit(player);
            }
            outputView.displayParticipantAndCards(player);
            outputView.displayEmptyLine();
        } while (answer == Answer.YES);
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
