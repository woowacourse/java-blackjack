package blackjack.domain.dto;

import blackjack.domain.Name;
import blackjack.domain.Outcome;

public class OutcomeDto {

    private final Name name;
    private final Outcome outcome;

    public OutcomeDto(final Name name, final Outcome outcome) {
        this.name = name;
        this.outcome = outcome;
    }

    public Name getName() {
        return name;
    }

    public Outcome getOutcome() {
        return outcome;
    }
}
