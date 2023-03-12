package blackjack.controller;

import blackjack.domain.card.ShuffledDeck;
import blackjack.domain.game.Bets;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public final class BlackjackController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

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
        Retry retry = this.retry;
        while (retry.isRepeatable()) {
            try {
                blackjackGame.addPlayers(inputView.readPlayers());
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw new IllegalArgumentException(this.retry.getFailMessage());
    }

    private void addBets() {
        for (final Name player : blackjackGame.getGamblerNames()) {
            addBet(player);
        }
    }

    private void addBet(final Name player) {
        Retry retry = this.retry;
        while (retry.isRepeatable()) {
            try {
                blackjackGame.addBet(player, inputView.readBet(player));
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
    }

    private void initialDraw() {
        blackjackGame.initialDraw(ShuffledDeck.getInstance());
        outputView.printInitialDraw(blackjackGame.getPlayers());
    }

    private void draw() {
        while (blackjackGame.isExistDrawablePlayer()) {
            final Player player = blackjackGame.findDrawablePlayer();
            final BlackjackCommand command = readCommand(player);
            decide(player, command);
            outputView.printPlayerDraw(player);
        }
        blackjackGame.drawToDealer(ShuffledDeck.getInstance());
        outputView.printDealerDraw(blackjackGame.getDealer());
        for (final Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerCardsWithScore(player);
        }
    }

    private BlackjackCommand readCommand(final Player player) {
        Retry retry = this.retry;
        while (retry.isRepeatable()) {
            try {
                return BlackjackCommand.from(inputView.readCommand(player));
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
                retry = retry.decrease();
            }
        }
        throw new IllegalArgumentException(retry.getFailMessage());
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
