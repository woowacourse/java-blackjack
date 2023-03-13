package blackjack.domain.betting;

public class BettingAmount {
    private static final int MIN_BETTING_AMOUNT = 0;
    private static final String MIN_BETTING_AMOUNT_ERROR_MESSAGE = "베팅 금액은 0 이하일 수 없습니다.";

    private final int bettingAmount;

    public BettingAmount(int bettingAmount) {
        validateBettingAmount(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void validateBettingAmount(int bettingAmount) {
        if (bettingAmount <= MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException(MIN_BETTING_AMOUNT_ERROR_MESSAGE);
        }
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
