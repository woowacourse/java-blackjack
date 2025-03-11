package blackjack.domain.card;

import static blackjack.domain.card.Score.BUST_THRESHOLD;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum WinningResult {
    DRAW((mainCards, subCards) -> {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore > BUST_THRESHOLD && subScore > BUST_THRESHOLD) {
            return true;
        }
        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return false;
        }
        if (!mainCards.isBlackjack() && subCards.isBlackjack()) {
            return false;
        }
        return mainScore == subScore;
    }),
    WIN((mainCards, subCards) -> {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore <= BUST_THRESHOLD && subScore > BUST_THRESHOLD) {
            return true;
        }

        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return true;
        }

        if (mainScore <= BUST_THRESHOLD) {
            return mainScore > subScore;
        }

        return false;
    }),
    LOSE((mainCards, subCards) -> {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore > BUST_THRESHOLD && subScore <= BUST_THRESHOLD) {
            return true;
        }

        if (!mainCards.isBlackjack() && subCards.isBlackjack()) {
            return true;
        }

        if (mainScore <= BUST_THRESHOLD) {
            return mainScore < subScore;
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
