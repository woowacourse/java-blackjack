package blackjack.domain.dto;

import java.util.Map;

public class PlayerRecordDto {
    private final Map<String, String> outcome;

    public PlayerRecordDto(Map<String, String> outcome) {
        this.outcome = outcome;
    }

    public Map<String, String> getOutcome() {
        return outcome;
    }
}
