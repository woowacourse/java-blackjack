package blackjack.domain.card;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum WinningResult {
    DRAW((mainScore, subScore) -> {
        if (mainScore.isBust() && subScore.isBust()) {
            return true;
        }

        if (mainScore.isBlackjack() && !subScore.isBlackjack()) {
            return false;
        }
        if (!mainScore.isBlackjack() && subScore.isBlackjack()) {
            return false;
        }
        return mainScore.value() == subScore.value();
    }),
    WIN((mainScore, subScore) -> {
        if (!mainScore.isBust() && subScore.isBust()) {
            return true;
        }

        if (mainScore.isBlackjack() && !subScore.isBlackjack()) {
            return true;
        }

        if (!mainScore.isBust()) {
            return mainScore.isGreaterThan(subScore);
        }
        return false;
    }),
    LOSE((mainScore, subScore) -> {
        if (mainScore.isBust() && !subScore.isBust()) {
            return true;
        }

        if (!mainScore.isBlackjack() && subScore.isBlackjack()) {
            return true;
        }

        if (!mainScore.isBust()) {
            return mainScore.isLessThan(subScore);
        }
        return false;
    });

    private final BiFunction<BlackjackScore, BlackjackScore, Boolean> isMatch;

    WinningResult(BiFunction<BlackjackScore, BlackjackScore, Boolean> isMatch) {
        this.isMatch = isMatch;
    }

    public static WinningResult decide(BlackjackScore mainBlackjackScore, BlackjackScore subBlackjackScore) {
        return Arrays.stream(values())
                .filter(winningResult -> winningResult.isMatch.apply(mainBlackjackScore, subBlackjackScore))
                .findFirst()
                .orElseThrow();
    }
}
