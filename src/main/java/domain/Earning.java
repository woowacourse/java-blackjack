package domain;

public record Earning(long amount) {
    public Earning calculateProfit(WinningStatus winningStatus) {
        long profitAmount = (long) (this.amount * winningStatus.getPayoutRatio());
        return new Earning(profitAmount);
    }
}