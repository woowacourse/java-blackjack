package model.participant;

import model.Deck.Card;

public final class Dealer extends Participant {
    private static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Card getFirstHand() {
        return getParticipantHand().getCards().getFirst();
    }

    public boolean canHit() {
        return getParticipantHand().checkScoreBelow(DEALER_HIT_THRESHOLD);
    }
}

