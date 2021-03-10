package blackjack.dto;

import blackjack.domain.GameResult;

import java.util.Map;

public class DealerResultDto {
    private String name;
    private Map<GameResult, Long> result;

    public DealerResultDto(String name, Map<GameResult, Long> result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public Map<GameResult, Long> getResult() {
        return result;
    }
}
