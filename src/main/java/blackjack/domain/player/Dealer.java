package blackjack.domain.player;


public class Dealer extends AbstractPlayer {
    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_BOUND = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isCanDraw() {
        return getValue() <= DRAW_BOUND;
    }
}