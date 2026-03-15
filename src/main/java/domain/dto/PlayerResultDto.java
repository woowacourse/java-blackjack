package domain.dto;

import domain.result.ProfitResult;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public record PlayerResultDto(Map<String, Long> resultMap) {
    public static PlayerResultDto from(ProfitResult profitResult) {
        Map<String, Long> playerResultMap = profitResult.getPlayerProfitMap();
        return new PlayerResultDto(Collections.unmodifiableMap(new LinkedHashMap<>(playerResultMap)));
    }
}
