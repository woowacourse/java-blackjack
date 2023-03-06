package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Players players = createPlayers(new Retry());
        final BlackjackGame blackjackGame = new BlackjackGame(players);

        initialDraw(blackjackGame);
        draw(blackjackGame);
        printPlayerResult(blackjackGame);
        play(blackjackGame);
    }

    private Players createPlayers(final Retry retry) {
        while (retry.isRepeatable()) {
            try {
                return Players.from(inputView.readPlayers(), ShuffledDeck.getInstance());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
    }

    private void initialDraw(final BlackjackGame blackjackGame) {
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw(final BlackjackGame blackjackGame) {
        final Players players = blackjackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            drawTo(player);
        }
        blackjackGame.drawByDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(players.getDealer());
    }

    private void drawTo(final Player player) {
        while (isDrawable(player)) {
            final BlackjackCommand command = createCommand(player, new Retry());
            drawOnce(player, command);
            outputView.printDrawResult(player);
        }
    }

    private BlackjackCommand createCommand(final Player player, final Retry retry) {
        while (retry.isRepeatable()) {
            try {
                return BlackjackCommand.from(inputView.readCommand(player));
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
    }

    private boolean isDrawable(final Player player) {
        return player.isDrawable() && !player.isDealer();
    }

    private void drawOnce(final Player player, final BlackjackCommand command) {
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
}
