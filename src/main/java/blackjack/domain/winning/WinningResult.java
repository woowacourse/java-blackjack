package blackjack.domain.winning;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.function.Function;

public enum WinningResult {
    DRAW((battingAmount) -> battingAmount),
    WIN((battingAmount) -> battingAmount * 2),
    BLACKJACK_WIN((battingAmount) -> (int) (battingAmount * 1.5)),
    LOSE((battingAmount) -> 0);

    private final Function<Integer, Integer> winningAmountExpression;

    WinningResult(Function<Integer, Integer> winningAmountExpression) {
        this.winningAmountExpression = winningAmountExpression;
    }

    public static WinningResult decide(Cards playerCards, Cards dealerCards) {
        if (playerCards.isBust()) {
            return WinningResult.LOSE;
        }
        if (dealerCards.isBust()) {
            return WinningResult.WIN;
        }
        if (playerCards.isBlackjack() && dealerCards.isBlackjack()) {
            return WinningResult.DRAW;
        }
        if (playerCards.isBlackjack()) {
            return WinningResult.BLACKJACK_WIN;
        }
        if (dealerCards.isBlackjack()) {
            return WinningResult.LOSE;
        }

        return compareScore(playerCards, dealerCards);
    }

    public int calculateWinningAmount(int battingAmount) {
        return winningAmountExpression.apply(battingAmount);
    }

    private static WinningResult compareScore(Cards playerCards, Cards dealerCards) {
        Score mainScore = playerCards.calculateMaxScore();
        Score subScore = dealerCards.calculateMaxScore();
        if (mainScore.compareTo(subScore) > 0) {
            return WinningResult.WIN;
        }
        if (mainScore.compareTo(subScore) < 0) {
            return WinningResult.LOSE;
        }
        return WinningResult.DRAW;
    }
}
