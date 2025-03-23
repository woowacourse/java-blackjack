package bet;

public class BetAmount {

    private static final int AMOUNT_UNIT = 1000;
    private static final int AMOUNT_LIMIT = 1_000_000;

    private final double amount;

    public BetAmount(double amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getValue() {
        return (int) amount;
    }

    private void validate(double amount) {
        validateUnit(amount);
        validateLimit(amount);
    }

    private void validateUnit(double amount) {
        if (amount % AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 " + AMOUNT_UNIT + "원 단위만 입력 가능합니다.");
        }
    }

    private void validateLimit(double amount) {
        if (amount > AMOUNT_LIMIT) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 100만원 이하만 입력 가능합니다.");
        }
    }

}
