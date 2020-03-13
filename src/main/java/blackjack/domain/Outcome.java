package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Outcome {
    WIN("승", (playerScore, dealerScore) -> playerScore > dealerScore, 1),
    DRAW("무", (playerScore, dealerScore) -> playerScore.equals(dealerScore)
        && playerScore != 0, 0),
    LOSE("패", (playerScore, dealerScore) -> playerScore < dealerScore
        || (playerScore.equals(dealerScore) && playerScore == 0), -1);

    private final String name;
    private final BiPredicate<Integer, Integer> compare;
    private final int value;

    Outcome(String name, BiPredicate<Integer, Integer> compare, int value) {
        this.name = name;
        this.compare = compare;
        this.value = value;
    }

    public static Outcome calculate(int playerScore, int dealerScore) {
        return Arrays.stream(values())
            .filter(outcome -> outcome.compare.test(playerScore, dealerScore))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public Outcome converse() {
        return Arrays.stream(values())
            .filter(outcome -> outcome.value * -1 == value)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public String getName() {
        return name;
    }
}
