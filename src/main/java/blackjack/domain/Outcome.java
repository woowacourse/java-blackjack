package blackjack.domain;

import java.util.List;

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

    public static int calculateOutcomeCount(final List<Outcome> outcomes, final Outcome target) {
        return (int) outcomes.stream()
                .filter(outcome -> outcome == target)
                .count();
    }
}
