package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.winning.WinningResult;

public record BattingMoney(
        int amount
) {
    public BattingMoney {
        if (amount < 1000 || amount % 1000 != 0) {
            throw new IllegalArgumentException("배팅금액은 1000원 단위여야합니다.");
        }
    }

    public int calculateRevenue(Cards cards, WinningResult winningResult) {
        if (cards.isBust()) {
            return -amount;
        }

        if (winningResult == WinningResult.WIN && cards.isBlackjack()) {
            return (int) (amount * 1.5) - amount;
        }

        if (winningResult == WinningResult.WIN) {
            return amount * 2 - amount;
        }

        if (winningResult == WinningResult.DRAW) {
            return 0;
        }

        return -amount;
    }
}
