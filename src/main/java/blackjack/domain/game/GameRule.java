package blackjack.domain.game;

public enum GameRule {
    INITIAL_CARD_COUNT(2),
    LIMIT_POINT_BEFORE_BUST(21),
    DEALER_LIMIT_POINT(16),
    SOFT_ACE(11);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public static boolean checkPossibilityOfHit(int point) {
        return point < GameRule.LIMIT_POINT_BEFORE_BUST.getValue();
    }

    public static boolean isBust(int point) {
        return point > GameRule.LIMIT_POINT_BEFORE_BUST.getValue();
    }

    public static boolean checkPossibilityOfDealerDrawing(int point) {
        return point <= DEALER_LIMIT_POINT.getValue();
    }

    public static int applyBustToPoint(int point) {
        if (point > LIMIT_POINT_BEFORE_BUST.value) {
            return 0;
        }
        return point;
    }

    public int getValue() {
        return value;
    }
}
