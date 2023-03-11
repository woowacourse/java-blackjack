package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.Bets;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.player.Name;
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
        final Map<Name, Money> result = new LinkedHashMap<>();
        for (final Name gambler : blackjackGame.getGamblerNames()) {
            result.put(gambler, readBet(gambler));
        }
        blackjackGame.addBets(result);
    }

    private Money readBet(final Name gambler) {
        retry.reset();
        while (retry.isRepeatable()) {
            try {
                return Money.initialBet(inputView.readBet(gambler));
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
        for (final Name gamblerName : blackjackGame.getGamblerNames()) {
            draw(gamblerName);
        }
        blackjackGame.drawToDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
        for (final Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerResult(player);
        }
    }

    private void draw(final Name gamblerName) {
        while (blackjackGame.isDrawable(gamblerName)) {
            final BlackjackCommand command = readCommand(gamblerName);
            decide(gamblerName, command);
            outputView.printDrawResult(gamblerName, blackjackGame.getGamblerCardSymbols(gamblerName));
        }
    }

    private BlackjackCommand readCommand(final Name name) {
        retry.reset();
        while (retry.isRepeatable()) {
            try {
                return BlackjackCommand.from(inputView.readCommand(name));
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
    }

    private void decide(final Name gamblerName, final BlackjackCommand command) {
        if (command.isHit()) {
            blackjackGame.drawTo(gamblerName, ShuffledDeck.getInstance());
            return;
        }
        blackjackGame.stay(gamblerName);
    }

    private void play() {
        final Bets bets = blackjackGame.play();
        outputView.printGameResult(bets);
    }
}
