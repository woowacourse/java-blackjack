package blackjack.domain;

public enum GameRule {
    INITIAL_CARD_COUNT(2),
    LIMIT_POINT(21),
    SOFT_ACE(11);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isBurst(int point) {
        return point > GameRule.LIMIT_POINT.getValue();
    }
}
