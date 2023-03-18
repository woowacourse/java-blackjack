package domain;

public enum Number {
    LOW_ACE_VALUE(1),
    HIGH_ACE_VALUE(11),
    ACE_GAP(HIGH_ACE_VALUE.number - LOW_ACE_VALUE.number),
    INIT_HAND_COUNT(2),
    BLACKJACK_RESULT_VALUE(-1),
    BUST_VALUE(0),
    DEALER_MINIMUM_VALUE(17),
    BLACKJACK_HAND_VALUE(21),
    BUST_BOUNDARY_VALUE(21);
    private final int number;

    Number(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }
}
