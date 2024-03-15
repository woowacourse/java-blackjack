package domain;

public class Bet {

    static final int MIN_BET = 1;
    static final int BET_UNIT = 10;
    static final String BET_RANGE_MESSAGE = String.format("배팅 금액은 %d 보다 커야합니다.", MIN_BET);
    static final String BET_UNIT_MESSAGE = String.format("배팅 금액은 %d 단위여야 합니다.", BET_UNIT);

    private final int amount;

    public Bet(int amount) {
        validateRange(amount);
        validateUnit(amount);
        this.amount = amount;
    }

    void validateRange(int amount) {
        if (amount < MIN_BET) {
            throw new IllegalArgumentException(BET_RANGE_MESSAGE);
        }
    }

    void validateUnit(int amount) {
        if (amount % BET_UNIT != 0) {
            throw new IllegalArgumentException(BET_UNIT_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
