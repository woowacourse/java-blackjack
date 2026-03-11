package domain.strategy;

public class DealerDrawStrategy implements DrawStrategy {
    private static final int DEALER_DRAW_LIMIT = 16;

    @Override
    public boolean canDraw(int score) {
        if (score <= DEALER_DRAW_LIMIT) {
            return true;
        }

        return false;
    }
}
