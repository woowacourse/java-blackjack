package model.hand;

public final class HardHand extends ParticipantHand {

    public HardHand() {
        super();
    }

    @Override
    public Score calculateDefaultScore() {
        return Score.calculateDefault(cards);
    }

    @Override
    public Score calculateFinalScore() {
        return Score.calculateDefault(cards);
    }

    @Override
    public ParticipantHand cloneToSoftHand() {
        return new SoftHand(cards);
    }
}
