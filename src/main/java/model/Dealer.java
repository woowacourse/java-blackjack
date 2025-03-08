package model;


public class Dealer extends Participant {
    private static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Card openFirstCard() {
        return getParticipantHand().openFirstCard();
    }

    public boolean checkScoreUnderSixteen() {
        return getParticipantHand().checkScoreBelow(DEALER_HIT_THRESHOLD);
    }
}

