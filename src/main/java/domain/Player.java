package domain;

public class Player extends Participant {
    private static final int HIT_THRESHOLD = 20;

    private Player(Name name) {
        super(name);
    }

    public static Player from(String name) {
        return new Player(new Name(name));
    }

    @Override
    protected int getHitThreshold() {
        return HIT_THRESHOLD;
    }
}
