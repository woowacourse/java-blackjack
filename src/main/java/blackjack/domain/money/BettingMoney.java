package blackjack.domain.money;

import blackjack.domain.WinningStatus;

public class BettingMoney {
    
    private static final Money BETTING_MINIMUM_MONEY = new Money(10_000);
    private static final Money BETTING_MAXIMUM_MONEY = new Money(1_000_000);
    private static final double BLACKJACK_WINNING_PROFIT_RATE = 1.5;
    private static final double WINNING_PROFIT_RATE = 1.0;
    private static final double DRAWING_PROFIT_RATE = 0;
    private static final double LOSING_PROFIT_RATE = -1.0;
    
    private final Money money;

    public BettingMoney(int money) {
        this.money = new Money(money);
        validateMoney(this.money);
    }

    private void validateMoney(Money money) {
        if (money.isLessThan(BETTING_MINIMUM_MONEY) || money.isGreaterThan(BETTING_MAXIMUM_MONEY)) {
            throw new IllegalArgumentException("배팅은 10,000원부터 1,000,000원까지 가능합니다.");
        }
    }

    public Money getProfit(WinningStatus winningStatus) {
        if (winningStatus.equals(WinningStatus.BLACKJACK_WIN)) {
            return money.multiply(BLACKJACK_WINNING_PROFIT_RATE);
        }
        if (winningStatus.equals(WinningStatus.WIN)) {
            return money.multiply(WINNING_PROFIT_RATE);
        }
        if (winningStatus.equals(WinningStatus.DRAW)) {
            return money.multiply(DRAWING_PROFIT_RATE);
        }
        return money.multiply(LOSING_PROFIT_RATE);
    }
}
