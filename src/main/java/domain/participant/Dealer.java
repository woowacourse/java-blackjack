package domain.participant;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_DRAW_BOUND = 16;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= DEALER_DRAW_BOUND;
    }
}
