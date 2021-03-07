package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.controller.GameController.GAME_OVER_SCORE;

public enum Outcome {
    WIN("승", (dealerScore, playerScore) -> dealerScore > playerScore),
    LOSE("패", (dealerScore, playerScore) -> dealerScore < playerScore),
    DRAW("무", (dealerScore, playerScore) -> dealerScore == playerScore);

    private final String name;
    private final BiPredicate<Integer, Integer> compareFunction;

    Outcome(String name, BiPredicate<Integer, Integer> compareFunction) {
        this.name = name;
        this.compareFunction = compareFunction;
    }

    public String getName() {
        return name;
    }

    public static Outcome findOutcome(int dealerScore, int playerScore) {
        Outcome outcome = getOutcomeWhenBuster(dealerScore, playerScore);
        if (outcome != null) {
            return outcome;
        }
        return Arrays.stream(values())
                .filter(value -> value.compareFunction.test(dealerScore, playerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("예측되는 승패를 찾을 수 없습니다."));
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
