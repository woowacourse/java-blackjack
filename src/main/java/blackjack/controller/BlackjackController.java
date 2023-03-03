package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.BlackjackGameResult;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.ShuffledDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;
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
        play(blackjackGame);
    }

    private BlackjackGame generateBlackjackGame() {
        final Players players = repeatUntilGetValidInput(inputView::readPlayers);
        return new BlackjackGame(players);
    }

    private void initialDraw(final BlackjackGame blackjackGame) {
        blackjackGame.initialDraw(ShuffledDeck.getInstance());
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw(final BlackjackGame blackjackGame) {
        final Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            drawBy(player);
        }
        blackjackGame.drawByDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(players.getDealer());
    }

    private void drawBy(final Player player) {
        while (isDrawable(player)) {
            final BlackjackCommand command = repeatUntilGetValidInput(() -> inputView.readCommand(player));
            drawOnce(player, command);
            outputView.printDrawResult(player);
        }
    }

    private static boolean isDrawable(final Player player) {
        return player.isDrawable() && !player.isDealer();
    }

    private static void drawOnce(final Player player, final BlackjackCommand command) {
        if (command.isHit()) {
            player.draw(ShuffledDeck.getInstance());
            return;
        }
        player.stay();
    }

    private void printPlayerResult(final BlackjackGame blackjackGame) {
        final Players players = blackjackGame.getPlayers();
        for (final Player player : players.getPlayers()) {
            outputView.printPlayerResult(player);
        }
    }

    private void play(final BlackjackGame blackjackGame) {
        final BlackjackGameResult result = blackjackGame.play();
        outputView.printGameResult(result);
    }

    private <T> T repeatUntilGetValidInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatUntilGetValidInput(supplier);
        }
    }
}
