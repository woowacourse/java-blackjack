package blackjack.domain.player;

public class Dealer extends Player {

    private static final int DRAWABLE_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isDrawable() {
        return getScore() < DRAWABLE_THRESHOLD;
    }


}
