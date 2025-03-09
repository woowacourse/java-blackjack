package model.participant;

import java.util.List;
import model.Deck.Card;

public final class Dealer extends Participant {
    private static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    @Override
    public boolean canHit() {
        return participantHand.checkScoreBelow(DEALER_HIT_THRESHOLD);
    }

    @Override
    public List<Card> openInitialDeal() {
        return List.of(participantHand.getCards().getFirst());
    }
}

