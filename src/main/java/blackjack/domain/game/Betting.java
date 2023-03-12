package blackjack.domain.game;

public class Betting {

    private static final int BETTING_MIN_VALUE = 1_000;
    private static final int BETTING_MAX_VALUE = 100_000_000;

    private final int value;

    public Betting(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < BETTING_MIN_VALUE || value > BETTING_MAX_VALUE) {
            throw new IllegalArgumentException("배팅 금액은 최소 1천원, 최대 1억원까지 가능합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
