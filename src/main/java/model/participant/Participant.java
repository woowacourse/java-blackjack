package model.participant;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import model.Deck.Card;
import model.Deck.Deck;

public class Participant {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;
    private final ParticipantHand participantHand;

    public Participant() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(final Card card) {
        participantHand.add(card);
    }

    public void dealInitialCards(final Deck deck) {
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
    }

    public boolean isBurst() {
        return participantHand.checkBurst();
    }
    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    public List<Card> getHandCards() {
        return Collections.unmodifiableList(participantHand.getCards());
    }

    public ParticipantHand getParticipantHand() {
        return participantHand;
    }
}
