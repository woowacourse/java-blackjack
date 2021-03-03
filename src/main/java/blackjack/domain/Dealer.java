package blackjack.domain;


public class Dealer extends AbstractPlayer {
    private static final int DRAW_BOUND = 16;

    @Override
    public boolean isCanDraw() {
        return getValue() <= DRAW_BOUND;
    }
}
