package blackjack.domain.participants;

import blackjack.domain.winning.WinningResult;

public record BettingMoney(
        int amount
) {
    public BettingMoney {
        if (amount < 1000 || amount % 1000 != 0) {
            throw new IllegalArgumentException("배팅금액은 1000원 단위여야합니다.");
        }
    }

    public int calculateProfit(WinningResult winningResult) {
        return winningResult.calculateWinningAmount(amount) - amount;
    }
}
