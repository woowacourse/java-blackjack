package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Outcome {

    WIN, LOSE, PUSH;

    public static List<Outcome> reverse(final List<Outcome> outcomes) {
        return outcomes.stream()
                .map(Outcome::reverse)
                .toList();
    }

    private static Outcome reverse(final Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return Outcome.LOSE;
        }
        if (outcome == Outcome.LOSE) {
            return Outcome.WIN;
        }
        return Outcome.PUSH;
    }

    public static Map<Outcome, Long> countByKind(final List<Outcome> outcomes) {
        return Collections.unmodifiableMap(outcomes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
}
