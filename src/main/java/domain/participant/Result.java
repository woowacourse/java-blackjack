package domain.participant;

import java.util.function.BiPredicate;

public enum Result {
    WIN(1, (playerScore, dealerScore) -> playerScore > dealerScore),
    LOSE(-1, (playerScore, dealerScore) -> playerScore < dealerScore),
    PUSH(0, (playerScore, dealerScore) -> playerScore == dealerScore),
    BLACKJACK(1.5, (playerScore, dealerScore) -> playerScore == 21);

    private final double multipleValue;
    private final BiPredicate<Integer, Integer> condition;

    Result(double multipleValue, BiPredicate<Integer, Integer> condition) {
        this.multipleValue = multipleValue;
        this.condition = condition;
    }
}
