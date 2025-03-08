package blackjack.domain;

public enum GameRule {
    INITIAL_CARD_DECK_COUNT(6),
    INITIAL_DEALING_CARD_COUNT(2),

    PLAYER_LIMIT_POINT(21),
    DEALER_LIMIT_POINT(16);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public static boolean isBust(int point) {
        return point > GameRule.PLAYER_LIMIT_POINT.getValue();
    }

    public int getValue() {
        return value;
    }
}
