package model;

public class Betting {
    private final long bettingAmount;

    public Betting(long bettingAmount) {
        checkBettingAmountPositive(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void checkBettingAmountPositive(long bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("베팅 금액을 음수로 넣으시면 안됩니다.");
        }
    }

    public long getBettingAmount() {
        return bettingAmount;
    }
}
