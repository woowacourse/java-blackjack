package domain.dto;

import domain.result.ProfitResult;

import java.util.Map;

public record BlackjackResultDto(
        long dealerBenefit,
        PlayerResultDto matchResultMap
) {
    public static BlackjackResultDto from(ProfitResult profitResult) {
        return new BlackjackResultDto(
                profitResult.getDealerBenefit(),
                PlayerResultDto.from(profitResult)
        );
    }

    public Map<String, Long> playerProfitMap() {
        return matchResultMap.resultMap();
    }
}
