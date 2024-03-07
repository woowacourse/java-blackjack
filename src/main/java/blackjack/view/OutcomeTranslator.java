package blackjack.view;

import blackjack.domain.Outcome;
import java.util.Arrays;

public enum OutcomeTranslator {
    WIN(Outcome.WIN, "승"),
    LOSE(Outcome.LOSE, "패"),
    PUSH(Outcome.PUSH, "무");

    private final Outcome outcome;

    private final String viewName;

    OutcomeTranslator(final Outcome outcome, final String viewName) {
        this.outcome = outcome;
        this.viewName = viewName;
    }

    public static String translate(final Outcome outcome) {
        return Arrays.stream(OutcomeTranslator.values())
                .filter(outcomeTranslator -> outcomeTranslator.outcome == outcome)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("매칭되는 결과가 없습니다."))
                .viewName;
    }

    public Outcome getOutcome() {
        return outcome;
    }
}
