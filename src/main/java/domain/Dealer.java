package domain;

public class Dealer extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 17;
    private static final String NAME = "딜러";

    @Override
    public boolean isDrawable() {
        return UPPER_BOUND_OF_DRAWABLE_SCORE > hand.calculateScore();
    }

    @Override
    public String name() {
        return NAME;
    }
}
