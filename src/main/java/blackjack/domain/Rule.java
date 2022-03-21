package blackjack.domain;

public enum Rule {

    WINNING_SCORE(21),
    DEALER_HIT_STANDARD_SCORE(16),
    ;

    private final int value;

    Rule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
