package domain.money;

import domain.result.WinningStatus;

public record Profit(Money money) {
    public static Profit from(long amount) {
        return new Profit(new Money(amount));
    }

    public static Profit from(Money money) {
        return new Profit(money);
    }

    // todo : 디미터의 법칙 적용
    public static Profit calculateProfit(BettingMoney bettingMoney, WinningStatus winningStatus) {
        if (winningStatus == WinningStatus.WIN) {
            return new Profit(bettingMoney.money());
        }
        if (winningStatus == WinningStatus.BLACKJACK_WIN) {
            return new Profit(bettingMoney.money().multiply(1.5));
        }
        if (winningStatus == WinningStatus.LOSE) {
            return new Profit(bettingMoney.money().multiply(-1));
        }
        return new Profit(bettingMoney.money().multiply(0));
    }

    public long getAmount() {
        return money.amount();
    }
}
