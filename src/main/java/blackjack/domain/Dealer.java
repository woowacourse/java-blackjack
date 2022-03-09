package blackjack.domain;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int DRAWABLE_BOUND = 16;

    public Dealer() {
        super(NAME);
    }

    public boolean isDrawable() {
        return getTotalNumber() <= DRAWABLE_BOUND;
    }
}
