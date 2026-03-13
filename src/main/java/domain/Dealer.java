package domain;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    private Dealer(Name name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(new Name(DEALER_NAME));
    }

    @Override
    public int getHitThreshold() {
        return HIT_THRESHOLD;
    }
}
