package blackjack.domain.money;

import blackjack.domain.WinningStatus;

public final class BlackjackBettingMoney {
    
    private static final int BETTING_MINIMUM_MONEY = 10_000;
    private static final int BETTING_MAXIMUM_MONEY = 1_000_000;
    
    private final Money bettingMoney;
    
    public BlackjackBettingMoney(int money) {
        validateMoney(money);
        this.bettingMoney = new Money(money);
    }

    private void validateMoney(int money) {
        if (money < BETTING_MINIMUM_MONEY || money > BETTING_MAXIMUM_MONEY) {
            throw new IllegalArgumentException("블랙잭 배팅은 10,000원부터 1,000,000원까지 가능합니다.");
        }
    }
    
    public Money getProfit(WinningStatus winningStatus) {
        return bettingMoney.multiply(winningStatus.getProfitFactor());
    }
}
