package blackjackgame.dto;

public class DealerResultDto {
    private final String outcome;
    private final int outcomeNumber;

    public DealerResultDto(final String outcome, final int outcomeNumber) {
        this.outcome = outcome;
        this.outcomeNumber = outcomeNumber;
    }

    public String getOutCome() {
        return outcome;
    }

    public int getOutcomeNumber() {
        return outcomeNumber;
    }
}
