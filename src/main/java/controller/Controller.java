package controller;

import domain.card.CardGenerator;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Participants;
import view.InputView;
import view.OutputView;

import java.util.function.Supplier;

public final class Controller {

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
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return retryOnError(supplier);
        }
    }

    private void initGame(final Participants participants, final Dealer dealer) {
        OutputView.printSetupGame(participants.getNames());
        dealer.takeCard(deck.dealCard());

        participants.drawCard(deck);

        OutputView.printPlayerCards(dealer.getName(), dealer.revealCards());
        participants.getParticipants()
                .forEach(this::printPlayerCards);
    }

    private void printPlayerCards(final Participant participant) {
        OutputView.printPlayerCards(participant.getName(), participant.revealCards());
    }

    private void playGame(final Participants participants, final Dealer dealer) {
        participants.getParticipants()
                .forEach(this::playParticipantTurn);

        playDealerTurn(dealer);
    }

    private void playParticipantTurn(final Participant participant) {
        while (participant.isInPlaying(isHit(participant))) {
            participant.takeCard(deck.dealCard());

            OutputView.printPlayerCards(participant.getName(), participant.showCards());
        }
    }

    private boolean isHit(final Participant participant) {
        return retryOnError(InputView.readCommand(participant.getName())::isValue);
    }

    private void playDealerTurn(final Dealer dealer) {
        if (dealer.isInPlaying(dealer.dealerIsHit())) {
            dealer.takeCard(deck.dealCard());
            OutputView.printDealerHit();
        }
    }

    private void printResult(final Participants participants, final Dealer dealer) {
        OutputView.printPlayerScore(dealer.getName(),
                dealer.showCards(),
                dealer.getScore());

        participants.getParticipants()
                .forEach(this::printPlayerScore);

        OutputView.printGameResult(participants.getResult(dealer));
    }

    private void printPlayerScore(final Participant participant) {
        OutputView.printPlayerScore(participant.getName(),
                participant.showCards(),
                participant.getScore());
    }
}
