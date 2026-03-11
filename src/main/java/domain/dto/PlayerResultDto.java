package domain.dto;

import domain.result.BlackjackResult;
import domain.result.MatchCase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record PlayerResultDto(Map<String, Long> resultMap) {
    public static PlayerResultDto from(BlackjackResult blackjackResult) {
        Map<String, Long> playerResultMap = blackjackResult.getPlayerProfitMap();
        return new PlayerResultDto(Map.copyOf(playerResultMap));
    }
}
