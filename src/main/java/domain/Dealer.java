package domain;

public class Dealer extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 17;

    public boolean isDrawable() {
        return UPPER_BOUND_OF_DRAWABLE_SCORE > hand.calculateScore();
    }
}
