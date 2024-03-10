package blackjack.dto;

import blackjack.domain.gamer.Name;
import blackjack.domain.Outcome;

public class PlayerOutcomeDto {

    private final Name name;
    private final Outcome outcome;

    public PlayerOutcomeDto(final Name name, final Outcome outcome) {
        this.name = name;
        this.outcome = outcome;
    }

    public Name getName() {
        return name;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public static PlayerOutcomeDto toDto(final Name name, final Outcome outcome) {
        return new PlayerOutcomeDto(name, outcome);
    }
}
