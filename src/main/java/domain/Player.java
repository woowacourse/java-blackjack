package domain;

public class Player extends Participant {
    private static final int BUST_THRESHOLD = 21;

    private Player(Name name) {
        super(name);
    }

    public static Player from(String name) {
        return new Player(new Name(name));
    }

    @Override
    protected int getBustThreshold() { return BUST_THRESHOLD; }
}
