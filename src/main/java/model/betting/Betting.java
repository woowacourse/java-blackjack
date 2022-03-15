package model.betting;

public class Betting {

    private static final int EXCLUDE_MINIMUM_BETTING = 0;
    private final long bettingAmount;
    public static final String BETTING_AMOUNT_LOWER_BOUND_MESSAGE =
            "베팅 금액는 " + EXCLUDE_MINIMUM_BETTING + "원보다 커야합니다.";

    public Betting(long bettingAmount) {
        checkBettingAmountPositive(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void checkBettingAmountPositive(long bettingAmount) {
        if (bettingAmount <= EXCLUDE_MINIMUM_BETTING) {
            throw new IllegalArgumentException(BETTING_AMOUNT_LOWER_BOUND_MESSAGE);
        }
    }

    public long getBettingAmount(BettingCalculateStrategy calculateStrategy) {
        return calculateStrategy.calculate(bettingAmount);
    }
}
