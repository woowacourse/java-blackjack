package domain.player;

public class Dealer extends Player {
    
    private static final int DEALER_INITIAL_OPEN_CARD_COUNT = 1;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public void openInitialCards() {
        openCards(DEALER_INITIAL_OPEN_CARD_COUNT);
    }

    public boolean canHit() {
        return computeOptimalSum() <= DEALER_HIT_THRESHOLD;
    }
}
