package blackjack.domain.gamer;

public class Dealer extends Gamer {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAW_THRESHOLD = 16;

    public boolean shouldDrawCard() {
        return calculateSum() <= DRAW_THRESHOLD;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}