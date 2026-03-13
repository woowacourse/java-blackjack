package model.paticipant;

public class NonBettingPlayer extends Participant implements Player {

    private static final int PLAYER_HIT_THRESHOLD = 21;

    public NonBettingPlayer(String name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() < PLAYER_HIT_THRESHOLD;
    }
}
