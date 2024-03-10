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

    public static int calculateWinCount(final List<Outcome> outcomes) {
        return (int) outcomes.stream()
                .filter(outcome -> outcome == Outcome.WIN)
                .count();
    }

    public static int calculateLoseCount(final List<Outcome> outcomes) {
        return (int) outcomes.stream()
                .filter(outcome -> outcome == Outcome.LOSE)
                .count();
    }

    public static int calculatePushCount(final List<Outcome> outcomes) {
        return (int) outcomes.stream()
                .filter(outcome -> outcome == Outcome.PUSH)
                .count();
    }
}
