package blackjack.domain.dto;

import java.util.Map;

public class DealerResultDto {
    private String name;
    private Map<String, Integer> outcome;

    public DealerResultDto(String name, Map<String, Integer> outcome) {
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
