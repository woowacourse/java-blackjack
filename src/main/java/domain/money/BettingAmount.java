package domain.money;

public class BettingAmount extends Money {

    private static final int BETTING_AMOUNT_MIN = 1000;

    private BettingAmount(int value) {
        super(value);
        validate(value);
    }

    public static BettingAmount valueOf(int bettingAmount) {
        return new BettingAmount(bettingAmount);
    }

    private void validate(int bettingAmount) {
        validateRange(bettingAmount);
        validateUnit(bettingAmount);
    }

    private void validateRange(int bettingAmount) {
        if (bettingAmount < BETTING_AMOUNT_MIN) {
            throw new IllegalArgumentException(String.format("베팅 금액은 %d원 이상이어야 합니다.", BETTING_AMOUNT_MIN));
        }
    }

    private void validateUnit(int bettingAmount) {
        if (bettingAmount % BETTING_AMOUNT_MIN != 0) {
            throw new IllegalArgumentException(String.format("베팅 금액은 %d원 단위여야 합니다.", BETTING_AMOUNT_MIN));
        }
    }
}
