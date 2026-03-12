package blackjack.domain;

public class BettingAmount {
    private final long bettingAmount;

    public BettingAmount(long bettingAmount) {
        this.bettingAmount = bettingAmount;
    }


    public long getBettingAmount() {
        return bettingAmount;
    }


    public long calculateEarningAmount(GameResult gameResult) {
        return (long) (bettingAmount * gameResult.getEarningRate());
    }
}
