package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.function.Supplier;

public final class Controller {

    private static final int BUST_NUMBER = 22;
    private static final int DEALER_STAY_NUMBER = 17;

    private final Deck deck;

    public Controller(final CardGenerator cardGenerator) {
        this.deck = Deck.from(cardGenerator);
    }

    public void run() {
        Participants participants = retryOnError(() -> Participants.from(InputView.readPlayerNames()));
        Dealer dealer = Dealer.create();

        initGame(participants, dealer);
        playGame(participants, dealer);
        printResult(participants, dealer);
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
            return retryOnError(supplier);
        }
    }

    private void initGame(final Participants participants, final Dealer dealer) {
        OutputView.printSetupGame(participants.getNames());
        dealer.takeCard(deck.dealCard());

        participants.drawCard(deck);

        OutputView.printPlayerCards(dealer.getName(), dealer.displayCards());
        participants.getParticipants()
                .forEach(this::printPlayerCards);
    }

    private void printPlayerCards(final Participant participant) {
        OutputView.printPlayerCards(participant.getName(), participant.displayCards());
    }

    private void playGame(final Participants participants, final Dealer dealer) {
        participants.getParticipants()
                .forEach(this::playParticipantTurn);

        playDealerTurn(dealer);
    }

    private void playParticipantTurn(final Participant participant) {
        while (isKeepPlaying(participant)) {
            participant.takeCard(deck.dealCard());

            OutputView.printPlayerCards(participant.getName(), participant.displayCards());
        }
    }

    private boolean isKeepPlaying(final Participant participant) {
        return participant.getScore() < BUST_NUMBER && isHit(participant);
    }

    private boolean isHit(final Participant participant) {
        return retryOnError(() -> InputView.readCommand(participant.getName()).isValue());
    }

    private void playDealerTurn(final Dealer dealer) {
        int score = dealer.getScore();
        while (score < DEALER_STAY_NUMBER) {
            OutputView.printHitOrStay(score);
            dealer.takeCard(deck.dealCard());
            score = dealer.getScore();
        }
        OutputView.printHitOrStay(score);
    }

    private void printResult(final Participants participants, final Dealer dealer) {
        OutputView.printPlayerScore(dealer.getName(), dealer.displayCards(), dealer.getScore());

        participants.getParticipants().forEach(this::printPlayerScore);

        final Results results = Results.of(dealer.getScore(), participants.getParticipants());
        OutputView.printGameResult(results);
    }

    private void printPlayerScore(final Participant participant) {
        OutputView.printPlayerScore(participant.getName(), participant.displayCards(), participant.getScore());
    }
}
