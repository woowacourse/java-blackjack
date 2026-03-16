package domain;

public record Money(long amount) {
    public Money calculateProfit(WinningStatus winningStatus) {
        long profitAmount = (long) (this.amount * winningStatus.getPayoutRatio());
        return new Money(profitAmount);
    }
}