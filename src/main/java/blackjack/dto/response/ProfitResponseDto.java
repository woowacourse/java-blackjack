package blackjack.dto.response;

import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.gamer.Gamer;

public record ProfitResponseDto(
    Map<String, Double> profits
) {

    public static ProfitResponseDto of(Map<Gamer, Double> profits) {
        return new ProfitResponseDto(profits.entrySet().stream()
            .collect(Collectors.toMap(
                profit -> profit.getKey().getName(),
                Map.Entry::getValue)));
    }
}
