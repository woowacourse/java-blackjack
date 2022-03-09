package blackjack.domain;

import java.util.Arrays;

public enum Result {

    LOSE((userScore, dealerScore) -> userScore > 21 || (dealerScore <= 21 && userScore < dealerScore)),
    WIN((userScore, dealerScore) -> dealerScore > 21 || userScore > dealerScore),
    DRAW((userScore, dealerScore) -> userScore == dealerScore);

    private ScoreComparator comparator;

    Result(ScoreComparator comparator) {
        this.comparator = comparator;
    }

    public static Result check(int userScore, int dealerScore) {
        return Arrays.stream(values())
                .filter(result -> result.comparator.compare(userScore, dealerScore))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
