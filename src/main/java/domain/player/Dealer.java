package domain.player;


public class Dealer extends Player {

    private static final int INITIAL_CARD_COUNT = 1;
    private static final int DEALER_STOP_SCORE = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super();
    }

    public boolean canStand() {
        return score() >= DEALER_STOP_SCORE;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    protected int getInitialCardCount() {
        return INITIAL_CARD_COUNT;
    }
}
