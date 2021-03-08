package blackjack.dto;

import blackjack.domain.result.MatchResult;

import java.util.Map;

public class DealerResultDto {
    private final Map<MatchResult, Integer> result;

    public DealerResultDto(Map<MatchResult, Integer> result) {
        this.result = result;
    }

    public Map<MatchResult, Integer> getResult() {
        return result;
    }
}
