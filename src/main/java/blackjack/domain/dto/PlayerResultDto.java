package blackjack.domain.dto;

import java.util.Map;

public class PlayerResultDto {
    private final Map<String, String> outcome;

    public PlayerResultDto(Map<String, String> outcome) {
        this.outcome = outcome;
    }

    public Map<String, String> getOutcome() {
        return outcome;
    }
}
