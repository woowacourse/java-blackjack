package dto;

import domain.GameResult;
import java.util.List;
import java.util.Map;

public record PlayerResultDto(
        String name,
        String result
) {
    public static List<PlayerResultDto> from(Map<String, GameResult> playerResults) {
        return playerResults.entrySet().stream()
                .map(playerResult -> new PlayerResultDto(playerResult.getKey(), playerResult.getValue().getValue()))
                .toList();
    }
}
