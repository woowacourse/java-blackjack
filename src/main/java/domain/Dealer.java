package domain;

public class Dealer extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 17;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean isDrawable() {
        return UPPER_BOUND_OF_DRAWABLE_SCORE > score();
    }
}
