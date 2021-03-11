package money;

public class BattingMoney {
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
}
