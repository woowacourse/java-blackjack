package blackjack.domain;

public enum Rule {

    WINNING_SCORE(21),
    DIFFERENCE_IN_ACE_SCORE(11 - 1),
    DEALER_HIT_STANDARD_SCORE(16),
    MIN_PARTICIPANTS_SIZE(2),
    MAX_PARTICIPANTS_SIZE(8),
    ;

    private final int value;

    Rule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
