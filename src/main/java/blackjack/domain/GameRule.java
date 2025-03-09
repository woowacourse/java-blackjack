package blackjack.domain;

public enum GameRule {

    BUST_THRESHOLD_POINT(21),
    INITIAL_DEALING_CARD_COUNT(2);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public static boolean isBust(int point) {
        return point > GameRule.BUST_THRESHOLD_POINT.getValue();
    }

    public int getValue() {
        return value;
    }
}
