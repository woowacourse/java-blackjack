package model;

public class Betting {
    private static final int INIT_PROFIT_AMOUNT = 0;
    private final long bettingAmount;
    private final long profitAmount;

    private Betting(long bettingAmount, long profitAmount) {
        checkBettingAmountPositive(bettingAmount);
        this.bettingAmount = bettingAmount;
        this.profitAmount = profitAmount;
    }

    public Betting(long bettingAmount) {
        this(bettingAmount, INIT_PROFIT_AMOUNT);
    }

    private void checkBettingAmountPositive(long bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("베팅 금액을 음수로 넣으시면 안됩니다.");
        }
    }

    public Betting bet(Dealer dealer) {
        dealer.receiveBet(bettingAmount);
        Betting bettingAfterBet = new Betting(bettingAmount, profitAmount - bettingAmount);
        return bettingAfterBet;
    }

    public long getBettingAmount() {
        return bettingAmount;
    }

    public long getProfitAmount() {
        return profitAmount;
    }
}
