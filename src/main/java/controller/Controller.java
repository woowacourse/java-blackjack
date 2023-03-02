package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public final class Controller {

    private static final int BUST_NUMBER = 21;
    private static final int DEALER_STAY_NUMBER = 17;

    private final Deck deck;

    public Controller(final CardGenerator cardGenerator) {
        this.deck = Deck.from(cardGenerator);
    }

    public void run() {
        Participants participants = Participants.from(readPlayerNames());
        Dealer dealer = Dealer.create();

        initGame(participants, dealer);
        playGame(participants, dealer);
        printResult(participants, dealer);
    }

    private List<String> readPlayerNames() {
        final List<String> playerNames = InputView.readPlayerNames();
        OutputView.printSetupGame(playerNames);
        return playerNames;
    }

    private void initGame(final Participants participants, final Dealer dealer) {
        dealer.takeCard(deck.drawCard());

        participants.drawCard(deck);

        OutputView.printPlayerCards(dealer.getName(), dealer.displayCards());

        participants.getParticipants()
                .forEach(this::printPlayerCards);
    }

    private void printPlayerCards(Participant participant) {
        OutputView.printPlayerCards(participant.getName(), participant.displayCards());
    }

    private void playGame(final Participants participants, final Dealer dealer) {
        participants.getParticipants()
                .forEach(this::playParticipantTurn);

        playDealerTurn(dealer);
    }

    private void playParticipantTurn(final Participant participant) {
        while (isKeepPlaying(participant)) {
            participant.takeCard(deck.drawCard());

            OutputView.printPlayerCards(participant.getName(), participant.displayCards());
        }
    }

    private static boolean isKeepPlaying(final Participant participant) {
        return participant.getScore() <= BUST_NUMBER && isHit(participant);
    }

    private static boolean isHit(final Participant participant) {
        return InputView.readCommand(participant.getName()).isValue();
    }

    private void playDealerTurn(final Dealer dealer) {
        int score = dealer.getScore();
        while (score < DEALER_STAY_NUMBER) {
            OutputView.printHitOrStay(score);
            dealer.takeCard(deck.drawCard());
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
