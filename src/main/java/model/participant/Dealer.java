package model.participant;

import model.card.Card;
import model.card.CardRank;

public class Dealer extends Participant {
    private static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Card openFirstCard() {
        return getParticipantHand().openFirstCard();
    }

    public boolean isFirstCardAce() {
        return openFirstCard().getCardRank() == CardRank.ACE;
    }

    public boolean checkScoreUnderSixteen() {
        return getParticipantHand().checkScoreBelow(DEALER_HIT_THRESHOLD);
    }
}

