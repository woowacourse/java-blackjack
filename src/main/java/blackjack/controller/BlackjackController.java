package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.Bets;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = initializeGame();
        initialDraw(blackjackGame);
        draw(blackjackGame);
        play(blackjackGame);
    }

    private BlackjackGame initializeGame() {
        final Players players = createPlayers(new Retry());
        final Bets bets = createBets(new Retry(), players);

        return new BlackjackGame(players, bets);
    }

    private Players createPlayers(final Retry retry) {
        while (retry.isRepeatable()) {
            try {
                return Players.from(inputView.readPlayers());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
    }

    private Bets createBets(final Retry retry, Players players) {
        final Map<Player, Money> result = new LinkedHashMap<>();
        for (final Player player : players.getGamblers()) {
            result.put(player, readPlayerBet(retry, player));
        }
        return new Bets(result);
    }

    private Money readPlayerBet(final Retry retry, final Player player) {
        while (retry.isRepeatable()) {
            try {
                return Money.initialBet(inputView.readBet(player));
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
    }

    private void initialDraw(final BlackjackGame blackjackGame) {
        blackjackGame.initialDraw(ShuffledDeck.getInstance());
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw(final BlackjackGame blackjackGame) {
        for (final Player player : blackjackGame.getPlayers()) {
            drawOnce(blackjackGame, player);
        }
        blackjackGame.drawToDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
        for (final Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerResult(player);
        }
    }

    private void drawOnce(final BlackjackGame blackjackGame, final Player player) {
        while (isDrawable(player)) {
            final BlackjackCommand command = createCommand(player, new Retry());
            decideAction(blackjackGame, player, command);
            outputView.printDrawResult(player);
        }
    }

    private boolean isDrawable(final Player player) {
        return player.isDrawable() && !player.isDealer();
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

    private void decideAction(final BlackjackGame blackjackGame, final Player player, final BlackjackCommand command) {
        if (command.isHit()) {
            blackjackGame.drawTo(player, ShuffledDeck.getInstance());
            return;
        }
        blackjackGame.stay(player);
    }

    private void play(final BlackjackGame blackjackGame) {
        final Bets bets = blackjackGame.play();
        outputView.printGameResult(bets);
    }
}
