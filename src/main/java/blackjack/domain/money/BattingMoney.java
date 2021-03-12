package blackjack.domain.money;

public class BattingMoney {
    private static final int ZERO_VALUE = 0;

    public static final BattingMoney ZERO = new BattingMoney(0);

    private final int value;

    public BattingMoney(final int value) {
        validateBattingMoney(value);
        this.value = value;
    }

    private void validateBattingMoney(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("배팅금액은 음수일 수 없습니다.");
        }
    }

    public BattingMoney add(final int battingMoney) {
        return new BattingMoney(value + battingMoney);
    }

    public boolean isZero() {
        return value == ZERO_VALUE;
    }

    public double multiply(final double multiplier) {
        return value * multiplier;
    }
}
