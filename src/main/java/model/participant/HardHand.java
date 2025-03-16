package model.participant;

public final class HardHand extends ParticipantHand {

    public HardHand() {
        super();
    }

    @Override
    public int calculateFinalScore() {
        return calculateDefaultScore();
    }

    @Override
    public ParticipantHand cloneToSoftHand() {
        return new SoftHand(cards);
    }
}
