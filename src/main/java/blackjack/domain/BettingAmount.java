package blackjack.domain;

public class BettingAmount {
    private final long bettingAmount;
    private final int MINIMUM_AMOUNT = 0;

    public BettingAmount(long bettingAmount) {
        validate(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    public long calculateEarningAmount(GameResult gameResult) {
        return (long) (bettingAmount * gameResult.getEarningRate());
    }

    private void validate(long bettingAmount) {
        if (bettingAmount <= MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("[ERROR]: 0원 초과의 값을 입력하세요.");
        }
    }
}
