package blackjack.model.wallet;

public abstract class BetWallet {

    private static final int MIN_BET_AMOUNT = 100;
    private static final int BET_AMOUNT_UNIT = 10;

    protected final int betAmount;
    protected int profitAmount;

    protected BetWallet(int betAmount) {
        validateMinimumBetAmount(betAmount);
        validateBetAmountUnit(betAmount);
        this.betAmount = betAmount;
        this.profitAmount = 0;
    }

    public int calculateNetProfit() {
        return profitAmount - betAmount;
    }

    public int getProfitAmount() {
        return profitAmount;
    }

    private void validateMinimumBetAmount(int betAmount) {
        if (betAmount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 배팅은 100원부터 가능합니다.");
        }
    }

    private void validateBetAmountUnit(int betAmount) {
        if (betAmount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅은 10원 단위로만 가능합니다.");
        }
    }
}
