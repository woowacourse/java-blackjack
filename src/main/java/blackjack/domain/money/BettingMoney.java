package blackjack.domain.money;

import blackjack.domain.WinningStatus;

public class BettingMoney {

    private final int money;

    public BettingMoney(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < 10000 || money > 1000000) {
            throw new IllegalArgumentException("배팅은 10,000원부터 1,000,000원까지 가능합니다.");
        }
    }

    public int getProfit(WinningStatus winningStatus) {
        if (winningStatus.equals(WinningStatus.BLACKJACK_WIN)) {
            return (int) (money * 1.5);
        }
        if (winningStatus.equals(WinningStatus.WIN)) {
            return money;
        }
        if (winningStatus.equals(WinningStatus.DRAW)) {
            return 0;
        }
        return -money;
    }
}
