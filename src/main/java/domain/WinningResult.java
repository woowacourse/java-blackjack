package domain;

import domain.card.Cards;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum WinningResult {
    WIN(WinningResult::isWin, 1),
    DRAW(WinningResult::isDraw, 0),
    LOSE(WinningResult::isLose, -1),
    BLACKJACK(WinningResult::isBlackjack, 1.5);

    private final BiPredicate<Cards, Cards> compare;
    private final double profitRatio;

    WinningResult(BiPredicate<Cards, Cards> compare,
                  double profitRatio) {
        this.compare = compare;
        this.profitRatio = profitRatio;
    }

    public static WinningResult of(Cards playerCards, Cards dealerCards) {
        return Arrays.stream(values())
                .filter(outcome -> outcome.compare.test(playerCards, dealerCards))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isWin(Cards playerCards, Cards dealerCards) {
        if (playerCards.isBust() || playerCards.isBlackJack()) {
            return false;
        }
        return dealerCards.isBust() || playerCards.isMoreThanScore(dealerCards);
    }

    private static boolean isDraw(Cards playerCards, Cards dealerCards) {
        return playerCards.matchScore(dealerCards) && !playerCards.isBust();
    }

    private static boolean isBlackjack(Cards playerCards, Cards dealerCards) {
        return playerCards.isBlackJack() && !dealerCards.isBlackJack();
    }

    private static boolean isLose(Cards playerCards, Cards dealerCards) {
        return !(isWin(playerCards, dealerCards) ||
                isDraw(playerCards, dealerCards) ||
                isBlackjack(playerCards, dealerCards));
    }

    public double getProfitRatio() {
        return profitRatio;
    }
}
