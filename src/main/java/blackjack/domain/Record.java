package blackjack.domain;

import static blackjack.domain.RecordFactory.MAX_SCORE;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Record {

    WIN("승", "패", (dealerScore, playerScore) -> {
        if (dealerScore > MAX_SCORE) {
            return playerScore <= MAX_SCORE;
        }
        return playerScore <= MAX_SCORE && playerScore > dealerScore;
    }),
    PUSH("무", "무", (dealerScore, playerScore) -> dealerScore.equals(playerScore) && playerScore <= MAX_SCORE),
    LOSS("패", "승", (dealerScore, playerScore) -> true);

    private final String name;
    private final String oppositeName;
    private final BiPredicate<Integer, Integer> predicate;

    Record(final String name, final String oppositeName,
           final BiPredicate<Integer, Integer> predicate) {
        this.name = name;
        this.oppositeName = oppositeName;
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

    public Record getOppositeName() {
        return Arrays.stream(Record.values())
                .filter(record -> record.name.equals(oppositeName))
                .findFirst()
                .orElseThrow();
    }
}
