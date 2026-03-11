package model;

public class Player extends Participant {

    private static final int PLAYER_HIT_THRESHOLD = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() < PLAYER_HIT_THRESHOLD;
    }
}
