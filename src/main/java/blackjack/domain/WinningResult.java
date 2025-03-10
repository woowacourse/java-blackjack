package blackjack.domain;


import static blackjack.domain.BlackjackRules.BUST_THRESHOLD;

import blackjack.domain.card.Cards;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum WinningResult {
    DRAW((mainCards, subCards) -> {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore > BUST_THRESHOLD.getSymbol() && subScore > BUST_THRESHOLD.getSymbol()) {
            return true;
        }
        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return false;
        }
        if (!mainCards.isBlackjack() && subCards.isBlackjack()) {
            return false;
        }
        if (mainScore == subScore) {
            return true;
        }
        return false;
    }),
    WIN((mainCards, subCards) -> {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore <= BUST_THRESHOLD.getSymbol() && subScore > BUST_THRESHOLD.getSymbol()) {
            return true;
        }

        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return true;
        }

        if (mainScore <= BUST_THRESHOLD.getSymbol()) {
            return mainScore > subScore;
        }

        return false;
    }),
    LOSE((mainCards, subCards) -> {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore > BUST_THRESHOLD.getSymbol() && subScore <= BUST_THRESHOLD.getSymbol()) {
            return true;
        }

        if (!mainCards.isBlackjack() && subCards.isBlackjack()) {
            return true;
        }

        if (mainScore <= BUST_THRESHOLD.getSymbol()) {
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
