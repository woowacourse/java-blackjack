package blackjack.domain.game;

public enum GameRule {
    INITIAL_CARD_COUNT(2),
    BUST_THRESHOLD(21),
    DEALER_DRAW_THRESHOLD(16),
    SOFT_ACE(11),
    MAX_PLAYER_COUNT(10);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public static boolean isBust(int point) {
        return point > GameRule.BUST_THRESHOLD.getValue();
    }

    public static boolean isBlackJack(int cardCountInHand, int point) {
        boolean isInitialHand = cardCountInHand == INITIAL_CARD_COUNT.getValue();
        boolean isMaxPoint = point == BUST_THRESHOLD.getValue();
        return isInitialHand && isMaxPoint;
    }

    public int getValue() {
        return value;
    }
}
