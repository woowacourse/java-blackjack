package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = generateBlackjackGame();
        initialDraw(blackjackGame);
        draw(blackjackGame);
        printPlayerResult(blackjackGame);
        printGameResult(blackjackGame);
    }

    private BlackjackGame generateBlackjackGame() {
        final Players players = repeatUntilGetValidInput(inputView::readPlayers);
        return new BlackjackGame(players, ShuffledDeck.getInstance());
    }

    private void initialDraw(final BlackjackGame blackjackGame) {
        blackjackGame.initialDraw();
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getPlayers();
        for (Player player : players) {
            drawByGambler(blackjackGame, player);
        }
        blackjackGame.drawByDealer();
        outputView.printDealerDraw(blackjackGame.getDealer());
    }

    private void drawByGambler(final BlackjackGame blackjackGame, final Player player) {
        while (isDrawable(player)) {
            final BlackjackCommand command = repeatUntilGetValidInput(() -> inputView.readCommand(player));
            blackjackGame.drawByGambler(player, command);
            outputView.printDrawResult(player);
        }
    }

    private boolean isDrawable(final Player player) {
        return player.isDrawable() && !player.isDealer();
    }

    private <T> T repeatUntilGetValidInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatUntilGetValidInput(supplier);
        }
    }

    private void printPlayerResult(final BlackjackGame blackjackGame) {
        final List<Player> players = blackjackGame.getPlayers();
        for (final Player player : players) {
            outputView.printPlayerResult(player);
        }
    }

    private void printGameResult(final BlackjackGame blackjackGame) {
        final BlackjackGameResult result = blackjackGame.play();
        outputView.printGameResult(result);
    }
}
