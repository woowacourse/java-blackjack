package controller;

import domain.card.RandomCardGenerator;
import domain.game.BlackjackGame;
import domain.player.Participant;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public final class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = initGame();
        playGame(blackjackGame);

    }


    private BlackjackGame initGame() {
        final List<String> participantNames = InputView.readPlayerNames();
        this.outputView.printSetupGame(participantNames);

        final BlackjackGame blackjackGame = BlackjackGame.from(participantNames, new RandomCardGenerator());
        blackjackGame.drawCard();

        this.outputView.printDealerCard(blackjackGame.getDealerCard());
        this.outputView.printPlayerCards(blackjackGame.getParticipants());

        return blackjackGame;
    }

    private void playGame(final BlackjackGame blackjackGame) {
        blackjackGame.playParticipantsTurn(this::isHit, outputView::printPlayerCard);
        blackjackGame.playDealerTurn(outputView::printHitOrStay);
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
        return retryOnError(() -> InputView.readCommand(participant.getName()).isValue());
    }
}
