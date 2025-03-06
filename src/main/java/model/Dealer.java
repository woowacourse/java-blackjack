package model;


public class Dealer extends Participant{
    private static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    public Card getFirstHand(){
        return getParticipantHand().getCards().getFirst();
    }

    public boolean checkScoreUnderSixteen() {
        return getParticipantHand().checkScoreBelow(DEALER_HIT_THRESHOLD);
    }
}

