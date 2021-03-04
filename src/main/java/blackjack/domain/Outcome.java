package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

import static blackjack.domain.Round.GAME_OVER_SCORE;

public enum Outcome {
    WIN("승", (a, b) -> a > b),
    LOSE("패", (a, b) -> a < b),
    DRAW("무", (a, b) -> a == b);

    private final String name;
    private final BiPredicate<Integer, Integer> biPredicate;

    Outcome(String name, BiPredicate<Integer, Integer> biPredicate) {
        this.name = name;
        this.biPredicate = biPredicate;
    }

    public static Outcome findOutcome(int dealerScore, int playerScore) {
        Outcome outcome = getOutcomeWhenBuster(dealerScore, playerScore);
        if (!Objects.isNull(outcome)) {
            return outcome;
        }
        return Arrays.stream(values())
                .filter(o -> o.biPredicate.test(dealerScore, playerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체크할수 없습!!"));
    }

    private static Outcome getOutcomeWhenBuster(int dealerScore, int playerScore) {
        if (dealerScore > GAME_OVER_SCORE && playerScore > GAME_OVER_SCORE) {
            return WIN;
        }

        if (dealerScore > GAME_OVER_SCORE) {
            return LOSE;
        }

        if (playerScore > GAME_OVER_SCORE) {
            return WIN;
        }
        return null;
    }

    public static Outcome reverseResult(Outcome outcome) {
        if (outcome == WIN) {
            return LOSE;
        }

        if (outcome == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public String getName() {
        return name;
    }

    public static boolean isWin(Outcome outcome) {
        return outcome == WIN;
    }

    public static boolean isLose(Outcome outcome) {
        return outcome == LOSE;
    }

    public static boolean isDraw(Outcome outcome) {
        return outcome == DRAW;
    }
}
