package blackjack.view.mapper;

import blackjack.domain.Outcome;
import java.util.Arrays;

public enum OutcomeMapper {
    WIN(Outcome.WIN, "승"),
    LOSE(Outcome.LOSE, "패"),
    PUSH(Outcome.PUSH, "무");

    private final Outcome outcome;
    private final String viewName;

    OutcomeMapper(final Outcome outcome, final String viewName) {
        this.outcome = outcome;
        this.viewName = viewName;
    }

    public static String mapToViewName(final Outcome outcome) {
        return Arrays.stream(OutcomeMapper.values())
                .filter(outcomeMapper -> outcomeMapper.outcome == outcome)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("매칭되는 결과가 없습니다."))
                .viewName;
    }

    public Outcome getOutcome() {
        return outcome;
    }
}
