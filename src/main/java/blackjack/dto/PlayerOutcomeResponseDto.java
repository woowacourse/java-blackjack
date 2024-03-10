package blackjack.dto;

import blackjack.domain.gamer.Name;
import blackjack.domain.Outcome;

public class PlayerOutcomeResponseDto {

    private final Name name;
    private final Outcome outcome;

    public PlayerOutcomeResponseDto(final Name name, final Outcome outcome) {
        this.name = name;
        this.outcome = outcome;
    }

    public Name getName() {
        return name;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public static PlayerOutcomeResponseDto toDto(final Name name, final Outcome outcome) {
        return new PlayerOutcomeResponseDto(name, outcome);
    }
}
