package model;

public class Dealer {
    private final ParticipantHand participantHand;

    public Dealer() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(Card card) {
        participantHand.add(card);
    }

    public boolean checkScoreUnderSixteen() {
        return participantHand.calculateScoreSum() <= 16;
    }

    public ParticipantHand getParticipantHand() {
        return participantHand;
    }
}

