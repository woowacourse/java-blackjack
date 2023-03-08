package domain.user;

public class Dealer extends Player {

    private static final int DEALER_THRESHOLD = 16;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer(final CardPool cardPool) {
        super(DEFAULT_NAME, cardPool);
    }

    public boolean needsHit() {
        return sumCardPool() <= DEALER_THRESHOLD;
    }
}
