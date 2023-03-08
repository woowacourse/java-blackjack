package batting;

public class Amount {
    public static final int MIN_AMOUNT_UNIT = 100;
    private final int amount;

    public Amount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < MIN_AMOUNT_UNIT) {
            throw new IllegalArgumentException("100이상의 정수만 입력 가능합니다.");
        }

        if (amount % MIN_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("100원 단위로 입력 가능합니다.");
        }
    }
}
