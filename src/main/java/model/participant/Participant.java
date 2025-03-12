package model.participant;

import java.util.List;
import java.util.stream.IntStream;
import model.deck.Card;
import model.deck.Deck;

public abstract class Participant {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    protected final ParticipantHand participantHand;

    public Participant() {
        this.participantHand = new ParticipantHand();
    }

    public abstract boolean canHit();

    public abstract List<Card> openInitialDeal();

    public void receiveCard(final Card card) {
        participantHand.add(card);
    }

    public boolean isBurst() {
        return participantHand.checkBurst();
    }

    public void dealInitialCards(final Deck deck) {
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
    }

    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }
}
