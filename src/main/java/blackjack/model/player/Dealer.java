package blackjack.model.player;

public class Dealer extends Player {
    public static final int DEALER_DRAWBLE_LIMIT = 16;

    public boolean isCardDrawable() {
        return calculatePoint() <= DEALER_DRAWBLE_LIMIT;
    }
}
