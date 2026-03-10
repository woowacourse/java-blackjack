package domain.dto;

import domain.result.BlackjackResult;
import domain.result.MatchCase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record PlayerResultDto(Map<String, String> resultMap) {
    public static PlayerResultDto from(BlackjackResult blackjackResult) {
        Map<String, MatchCase> playerResultMap = blackjackResult.getPlayerResultMap();
        Map<String, String> resultMap = playerResultMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getKorDisplayName(),
                        (existing, replacement) -> replacement,
                        LinkedHashMap::new
                ));

        return new PlayerResultDto(Map.copyOf(resultMap));
    }
}
