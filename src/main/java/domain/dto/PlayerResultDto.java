package domain.dto;

import domain.result.BlackjackResult;

import java.util.Map;

public record PlayerResultDto(Map<String, Long> resultMap) {
    public static PlayerResultDto from(BlackjackResult blackjackResult) {
        Map<String, Long> playerResultMap = blackjackResult.getPlayerProfitMap();
        return new PlayerResultDto(Map.copyOf(playerResultMap));
    }
}
