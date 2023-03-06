package blackjackgame.dto;

public class GuestResultDto {
    private final String name;
    private final String outcome;

    public GuestResultDto(final String name, final String outcome) {
        this.name = name;
        this.outcome = outcome;
    }

    public String getName() {
        return name;
    }

    public String getOutcome() {
        return outcome;
    }
}
