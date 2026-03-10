package domain.strategy;

public class PlayerDrawStrategy implements DrawStrategy {
    private static final int MAXIMUM_SCORE = 21;

    @Override
    public boolean canDraw(int score) {
        if (score < MAXIMUM_SCORE) {
            return true;
        }

        return false;
    }
}
