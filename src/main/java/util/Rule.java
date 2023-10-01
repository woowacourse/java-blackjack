package util;

public enum Rule {

    GOAL_SCORE(21),
    MIN_SCORE(1),
    INIT_GIVE_CARDS(2),
    DEALER_MORE_SCORE(16);

    private final int value;

    Rule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
