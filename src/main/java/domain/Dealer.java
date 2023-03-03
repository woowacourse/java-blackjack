package domain;

public class Dealer extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 17;

    @Override
    public boolean isDrawable() {
        return UPPER_BOUND_OF_DRAWABLE_SCORE > hand.calculateScore();
    }

    @Override
    String name() {
        return "딜러";
    }
}
