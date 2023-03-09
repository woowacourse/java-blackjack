package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.Bets;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public BlackjackController(
            final InputView inputView,
            final OutputView outputView,
            final BlackjackGame blackjackGame
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        initializeGame();
        initialDraw();
        draw();
        play();
    }

    private void initializeGame() {
        addPlayers(new Retry());
        addBets(new Retry());
    }

    private void addPlayers(final Retry retry) {
        while (retry.isRepeatable()) {
            try {
                blackjackGame.addPlayers(inputView.readPlayers());
                return;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
    }

    private void addBets(final Retry retry) {
        final Map<Player, Money> result = new LinkedHashMap<>();
        for (final Player player : blackjackGame.getGambler()) {
            result.put(player, readBet(retry, player));
        }
        blackjackGame.addBets(result);
    }

    private Money readBet(final Retry retry, final Player player) {
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

    private void initialDraw() {
        blackjackGame.initialDraw(ShuffledDeck.getInstance());
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw() {
        for (final Player player : blackjackGame.getPlayers()) {
            drawOnce(player);
        }
        blackjackGame.drawToDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
        for (final Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerResult(player);
        }
    }

    private void drawOnce(final Player player) {
        while (isDrawable(player)) {
            final BlackjackCommand command = createCommand(player, new Retry());
            decideAction(player, command);
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

    private void decideAction(final Player player, final BlackjackCommand command) {
        if (command.isHit()) {
            blackjackGame.drawTo(player, ShuffledDeck.getInstance());
            return;
        }
        blackjackGame.stay(player);
    }

    private void play() {
        final Bets bets = blackjackGame.play();
        outputView.printGameResult(bets);
    }
}
