package blackjack.domain;

public enum GameRule {
    INITIAL_CARD_COUNT(2),
    PLAYER_LIMIT_POINT(21),
    DEALER_LIMIT_POINT(16),
    SOFT_ACE(11);

    private final int value;

    GameRule(int value) {
        this.value = value;
    }

    public static boolean isBurst(int point) {
        return point > GameRule.PLAYER_LIMIT_POINT.getValue();
    }

    public static boolean shouldDrawCardToDealer(int point) {
        return point <= DEALER_LIMIT_POINT.getValue();
    }

    public int getValue() {
        return value;
    }


}
