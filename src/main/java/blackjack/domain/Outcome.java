package blackjack.domain;

import blackjack.domain.dto.OutcomesDto;
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

    public static OutcomesDto toDto(final List<Outcome> outcomes) {
        return new OutcomesDto(
                calculateWinCount(outcomes),
                calculateLoseCount(outcomes),
                calculatePushCount(outcomes)
        );
    }

    private static int calculateWinCount(final List<Outcome> outcomes) {
        return (int) outcomes.stream()
                .filter(outcome -> outcome == Outcome.WIN)
                .count();
    }

    private static int calculateLoseCount(final List<Outcome> outcomes) {
        return (int) outcomes.stream()
                .filter(outcome -> outcome == Outcome.LOSE)
                .count();
    }

    private static int calculatePushCount(final List<Outcome> outcomes) {
        return (int) outcomes.stream()
                .filter(outcome -> outcome == Outcome.PUSH)
                .count();
    }
}
