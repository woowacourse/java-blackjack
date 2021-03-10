package blackjack.domain;

import blackjack.domain.card.Score;

import java.util.Arrays;
import java.util.function.BiPredicate;


public enum Outcome {
    WIN("승",
            (dealerScore, playerScore) -> (playerScore.isBust()),
            (dealerScore, playerScore) -> dealerScore > playerScore) {
        @Override
        public Outcome reverse() {
            return LOSE;
        }
    },
    LOSE("패",
            (dealerScore, playerScore) -> (dealerScore.isBust()),
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
    private final BiPredicate<Score, Score> compareFunctionWhenBuster;
    private final BiPredicate<Integer, Integer> compareFunctionNotBuster;

    Outcome(String name, BiPredicate<Score, Score> compareFunctionWhenBuster, BiPredicate<Integer, Integer> compareFunctionNotBuster) {
        this.name = name;
        this.compareFunctionWhenBuster = compareFunctionWhenBuster;
        this.compareFunctionNotBuster = compareFunctionNotBuster;
    }

    public String getName() {
        return name;
    }

    public static Outcome findOutcome(Score dealerScore, Score playerScore) {
        return Arrays.stream(values())
                .filter(value -> value.compareFunctionWhenBuster.test(dealerScore, playerScore))
                .findAny()
                .orElse(findOutcomeNotBuster(dealerScore.toInt(), playerScore.toInt()));
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
