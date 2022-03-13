package blackjack.domain;

import static blackjack.domain.card.Cards.MAX_SCORE;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Record {

    WIN("승", (dealerScore, playerScore) -> {
        if (dealerScore > MAX_SCORE) {
            return playerScore <= MAX_SCORE;
        }
        return playerScore <= MAX_SCORE && playerScore > dealerScore;
    }),
    PUSH("무", (dealerScore, playerScore) -> dealerScore.equals(playerScore) && playerScore <= MAX_SCORE),
    LOSS("패", (dealerScore, playerScore) -> true);

    private final String name;
    private final BiPredicate<Integer, Integer> predicate;

    Record(final String name, final BiPredicate<Integer, Integer> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public static Record of(int dealerScore, int playerScore) {
        return Arrays.stream(values())
                .filter(it -> it.predicate.test(dealerScore, playerScore))
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}
