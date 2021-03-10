package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.controller.GameController.GAME_OVER_SCORE;

public enum Outcome {
    WIN("승",
            (dealerScore, playerScore) -> (playerScore > GAME_OVER_SCORE),
            (dealerScore, playerScore) -> dealerScore > playerScore) {
        @Override
        public Outcome reverse() {
            return LOSE;
        }
    },
    LOSE("패",
            (dealerScore, playerScore) -> (dealerScore > GAME_OVER_SCORE),
            (dealerScore, playerScore) -> dealerScore < playerScore) {
        @Override
        public Outcome reverse() {
            return WIN;
        }
    },
    DRAW("무",
            (dealerScore, playerScore) -> (false),
            (dealerScore, playerScore) -> dealerScore == playerScore) {
        @Override
        public Outcome reverse() {
            return DRAW;
        }
    };

    private final String name;
    private final BiPredicate<Integer, Integer> compareFunctionWhenBuster;
    private final BiPredicate<Integer, Integer> compareFunctionNotBuster;

    Outcome(String name, BiPredicate<Integer, Integer> compareFunctionWhenBuster, BiPredicate<Integer, Integer> compareFunctionNotBuster) {
        this.name = name;
        this.compareFunctionWhenBuster = compareFunctionWhenBuster;
        this.compareFunctionNotBuster = compareFunctionNotBuster;
    }

    public String getName() {
        return name;
    }

    public static Outcome findOutcome(int dealerScore, int playerScore) {
        return Arrays.stream(values())
                .filter(value -> value.compareFunctionWhenBuster.test(dealerScore, playerScore))
                .findAny()
                .orElse(findOutcomeNotBuster(dealerScore, playerScore));
    }

    private static Outcome findOutcomeNotBuster(int dealerScore, int playerScore) {
        return Arrays.stream(values())
                .filter(value -> value.compareFunctionNotBuster.test(dealerScore, playerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("예측되는 승패를 찾을 수 없습니다."));
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

    abstract public Outcome reverse();
}
