package blackjack.common;

public enum BlackJackRule {
    MAX_SCORE_NOT_BUST(21),
    DEALER_MAX_HITTABLE_POINT(16),
    INITIAL_CARD_SIZE(2);

    private final int value;

    BlackJackRule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
