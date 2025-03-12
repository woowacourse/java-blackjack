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

    public int calculateProfit(Cards cards, WinningResult winningResult) {
        return calculateWinningAmount(cards, winningResult) - amount;
    }

    private int calculateWinningAmount(Cards cards, WinningResult winningResult) {
        if (cards.isBust()) {
            return 0;
        }

        if (winningResult == WinningResult.WIN && cards.isBlackjack()) {
            return (int) (amount * 1.5);
        }

        if (winningResult == WinningResult.WIN) {
            return amount * 2;
        }

        if (winningResult == WinningResult.DRAW) {
            return amount;
        }

        return 0;
    }
}
