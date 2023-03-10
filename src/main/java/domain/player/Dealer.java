package domain.player;

public class Dealer extends Player {
    private static final int STOP_LOWER_BOUND = 17;
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DEFAULT_AMOUNT = 1;

    public Dealer() {
        super(new Name(DEALER_NAME), new Amount(DEALER_DEFAULT_AMOUNT));
    }

    public boolean isDealerDraw() {
        return score() < STOP_LOWER_BOUND;
    }
}
