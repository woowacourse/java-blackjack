package controller;

import domain.card.RandomCardGenerator;
import domain.game.BlackjackGame;

import domain.player.Participant;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public final class Runner {

    private final InputView inputView;
    private final OutputView outputView;

    public Runner(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = retryOnError(this::joinGame);
        startGame(blackjackGame);
        playGame(blackjackGame);
        judgeGameResult(blackjackGame);
    }

    private BlackjackGame joinGame() {
        final List<String> participantNames = this.inputView.readPlayerNames();
        this.outputView.printSetupGame(participantNames);

        return BlackjackGame.from(participantNames, new RandomCardGenerator());
    }

    private void startGame(final BlackjackGame blackjackGame) {
        blackjackGame.drawCards();
        this.outputView.printPlayerCards(blackjackGame.getPlayers());
    }

    private void playGame(final BlackjackGame blackjackGame) {
        blackjackGame.playParticipantsTurn(this::isHit, outputView::printPlayerCard);
        blackjackGame.playDealerTurn(outputView::printHitOrStay);
    }

    private void judgeGameResult(final BlackjackGame blackjackGame) {
        this.outputView.printPlayerScore(blackjackGame.getPlayers());
        this.outputView.printGameResult(blackjackGame.judgeResult());
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            this.outputView.printExceptionMessage(e.getMessage());
            return retryOnError(supplier);
        }
    }

    private boolean isHit(final Participant participant) {
        return retryOnError(() -> this.inputView.readCommand(participant.getName()).isValue());
    }
}
