package blackjack.domain.card;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum WinningResult {
    DRAW((mainCards, subCards) -> {
        Score mainScore = mainCards.calculateScore();
        Score subScore = subCards.calculateScore();

        if (mainScore.isBust() && subScore.isBust()) {
            return true;
        }

        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return false;
        }
        if (!mainCards.isBlackjack() && subCards.isBlackjack()) {
            return false;
        }
        return mainScore.value() == subScore.value();
    }),
    WIN((mainCards, subCards) -> {
        Score mainScore = mainCards.calculateScore();
        Score subScore = subCards.calculateScore();

        if (!mainScore.isBust() && subScore.isBust()) {
            return true;
        }

        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return true;
        }

        if (!mainScore.isBust()) {
            return mainScore.isGreaterThan(subScore);
        }
        return false;
    }),
    LOSE((mainCards, subCards) -> {
        Score mainScore = mainCards.calculateScore();
        Score subScore = subCards.calculateScore();

        if (mainScore.isBust() && !subScore.isBust()) {
            return true;
        }

        if (!mainCards.isBlackjack() && subCards.isBlackjack()) {
            return true;
        }

        if (!mainScore.isBust()) {
            return mainScore.isLessThan(subScore);
        }
        return false;
    });

    private final BiFunction<Cards, Cards, Boolean> isMatch;

    WinningResult(BiFunction<Cards, Cards, Boolean> isMatch) {
        this.isMatch = isMatch;
    }

    public static WinningResult decide(Cards mainCards, Cards subCards) {
        return Arrays.stream(values())
                .filter(winningResult -> winningResult.isMatch.apply(mainCards, subCards))
                .findFirst()
                .orElseThrow();
    }
}
