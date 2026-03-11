package domain.dto;

import domain.result.BlackjackResult;

import java.util.Map;

public record BlackjackResultDto(
        long dealerBenefit,
        PlayerResultDto matchResultMap
) {
    public static BlackjackResultDto from(BlackjackResult blackjackResult) {
        return new BlackjackResultDto(
                blackjackResult.getDealerBenefit(),
                PlayerResultDto.from(blackjackResult)
        );
    }

    public Map<String, Long> playerProfitMap() {
        return matchResultMap.resultMap();
    }
}
