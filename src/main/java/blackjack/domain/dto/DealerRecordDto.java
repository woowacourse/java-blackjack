package blackjack.domain.dto;

import java.util.Map;

public class DealerRecordDto {
    private String name;
    private Map<String, Integer> outcome;

    public DealerRecordDto(String name, Map<String, Integer> outcome) {
        this.name = name;
        this.outcome = outcome;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getOutcome() {
        return outcome;
    }
}
