package domain.common;

public enum BlackJackRule {

    INITIAL_CARD_COUNT(2),
    BUST_NUMBER(21),
    ACE_WEIGHT(10),
    ;

    private final int value;

    BlackJackRule(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
