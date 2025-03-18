package blackjack.dto.response;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.gamer.Gamer;

public record ProfitResponseDto(
    Map<String, Double> profits
) {

    public static ProfitResponseDto of(Map<Gamer, Double> profits) {
        Map<String, Double> converted = new LinkedHashMap<>();
        for (var profit : profits.entrySet()) {
            converted.put(profit.getKey().getName(), profit.getValue());
        }
        return new ProfitResponseDto(converted);
    }
}
