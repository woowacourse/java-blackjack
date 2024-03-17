package domain.participant;

public class BettingAmount {
    private static final int MIN_BETTING_AMOUNT = 1;

    private final int bettingAmount;

    public BettingAmount(int bettingAmount) {
        validateMinBettingAmount(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void validateMinBettingAmount(int bettingAmount) {
        if (bettingAmount < MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("rejected value: %d - 최소 배팅 금액은 $%d입니다.", bettingAmount, MIN_BETTING_AMOUNT)
            );
        }
    }

    public int getValue() {
        return this.bettingAmount;
    }
}
