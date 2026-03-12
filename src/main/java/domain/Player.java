package domain;

public class Player extends Participant {
    private static final int BUST_THRESHOLD = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return getScore() <= BUST_THRESHOLD;
    }
}
