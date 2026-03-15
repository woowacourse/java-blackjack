package blackjack.dto;

import blackjack.domain.profit.ProfitResults;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record PlayerProfitsDto(Map<String, Integer> profits) {

    public static PlayerProfitsDto from(ProfitResults profitResults) {
        return new PlayerProfitsDto(profitResults.profitResults().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().getName(),
                        e -> e.getValue().value(),
                        (a, b) -> a,
                        LinkedHashMap::new)));
    }
}
