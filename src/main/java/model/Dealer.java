package model;


public class Dealer extends Participant{
    public Dealer() {
        super();
    }

    public Card getFirstHand(){
        return getParticipantHand().getCards().getFirst();
    }

    public boolean checkScoreUnderSixteen() {
        return getParticipantHand().checkScoreBelow(16);
    }
}

