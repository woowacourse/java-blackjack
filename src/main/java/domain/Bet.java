package domain;

public class Bet {

    public static final int MIN_BET = 0;
    public static final String BET_RANGE_MESSAGE = String.format("배팅 금액은 %d 보다 커야합니다.", MIN_BET);
    private final int amount;

    public Bet(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    void validateRange(int amount) {
        if (amount <= MIN_BET) {
            throw new IllegalArgumentException(BET_RANGE_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
