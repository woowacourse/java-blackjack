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

    private static final InputView inputView = InputView.getInstance();
    private static final OutputView outputView = OutputView.getInstance();

    private final BlackjackGame blackjackGame;
    private final Retry retry;

    public BlackjackController(final BlackjackGame blackjackGame, final Retry retry) {
        this.blackjackGame = blackjackGame;
        this.retry = retry;
    }

    public void run() {
        addPlayers();
        addBets();
        initialDraw();
        draw();
        play();
    }

    private void addPlayers() {
        retry.reset();
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

    private void addBets() {
        final Map<Player, Money> result = new LinkedHashMap<>();
        for (final Player player : blackjackGame.getGambler()) {
            result.put(player, readBet(player));
        }
        blackjackGame.addBets(result);
    }

    private Money readBet(final Player player) {
        retry.reset();
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
            draw(player);
        }
        blackjackGame.drawToDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
        for (final Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerResult(player);
        }
    }

    private void draw(final Player player) {
        while (isDrawable(player)) {
            final BlackjackCommand command = createCommand(player);
            decideAction(player, command);
            outputView.printDrawResult(player);
        }
    }

    private boolean isDrawable(final Player player) {
        return player.isDrawable() && !player.isDealer();
    }

    private BlackjackCommand createCommand(final Player player) {
        retry.reset();
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
