package model.participantV2;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import model.Deck.Card;
import model.Deck.Deck;

public abstract class ParticipantV2 {
    protected static final int INITIAL_DEAL_CARD_COUNT = 2;
    protected final ParticipantHand participantHand;

    /**
     * 사용 불가
     */
    public ParticipantV2() {
        this.participantHand = new ParticipantHand();
    }

    public abstract void receiveCard(final Card card);

    public abstract void dealInitialCards(final Deck deck);

    public abstract boolean isBurst();

    public abstract int calculateFinalScore();

    public abstract List<Card> getHandCards();

    public ParticipantHand getParticipantHand() {
        return participantHand;
    }
}
