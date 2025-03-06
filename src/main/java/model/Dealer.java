package model;


public class Dealer extends Participant{
    public Dealer() {
        super();
    }

    public boolean checkScoreUnderSixteen() {
        return getParticipantHand().checkScoreBelow(16);
    }
}

