package blackjack.dto;

import blackjack.domain.result.GameResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitDto {
    private final Map<String, Integer> result;

    private ProfitDto(Map<String, Integer> result) {
        this.result = result;
    }

    public static ProfitDto of(GameResult gameResult) {
        return new ProfitDto(gameResult.getProfitResults());
    }

    public Map<String, Integer> getResult() {
        return new LinkedHashMap<>(result);
    }
}
