package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.Bets;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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

    public void run(final Retry retry) {
        addPlayers(retry);
        addBets(retry);
        initialDraw();
        draw(retry);
        play();
    }

    private void addPlayers(Retry retry) {
        while (retry.isRepeatable()) {
            try {
                blackjackGame.addPlayers(inputView.readPlayers());
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw retry.getException();
    }

    private void addBets(final Retry retry) {
        for (final Name player : blackjackGame.getGamblerNames()) {
            addBet(player, retry);
        }
    }

    private void addBet(final Name player, Retry retry) {
        while (retry.isRepeatable()) {
            try {
                blackjackGame.addBet(player, inputView.readBet(player));
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw retry.getException();
    }

    private void initialDraw() {
        blackjackGame.initialDraw(ShuffledDeck.getInstance());
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw(final Retry retry) {
        while (blackjackGame.isExistDrawablePlayer()) {
            final Player player = blackjackGame.findDrawablePlayer();
            final BlackjackCommand command = readCommand(player, retry);
            decide(player, command);
            outputView.printPlayerDraw(player);
        }
        blackjackGame.drawToDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
        for (final Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerCardsWithScore(player);
        }
    }

    private BlackjackCommand readCommand(final Player player, Retry retry) {
        while (retry.isRepeatable()) {
            try {
                return BlackjackCommand.from(inputView.readCommand(player));
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw retry.getException();
    }

    private void decide(final Player player, final BlackjackCommand command) {
        if (command == BlackjackCommand.HIT) {
            blackjackGame.drawTo(player.name(), ShuffledDeck.getInstance());
            return;
        }
        blackjackGame.stay(player.name());
    }

    private void play() {
        final Bets bets = blackjackGame.play();
        outputView.printBetResult(bets, blackjackGame.getPlayers());
    }
}
