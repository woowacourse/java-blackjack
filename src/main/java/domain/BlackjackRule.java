package domain;

public enum BlackjackRule {
    DEALER_STANDARD_OF_HIT(17),
    INIT_CARD_COUNT(2),
    BUST_LIMIT(21),
    ACE_GAP(10),
    ;

    private final int value;

    BlackjackRule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
