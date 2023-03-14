package blackjack.domain.betting;

public class Betting {

    private static final int MINIMUM_BET = 100;
    private static final int MAXIMUM_BET = 1_000_000;

    private final int value;

    public Betting(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < MINIMUM_BET || MAXIMUM_BET < value) {
            throw new IllegalArgumentException("베팅은 " + MINIMUM_BET + "원 이상, " + MAXIMUM_BET + "원 이하만 가능합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
