package domain.dto;

import domain.result.BlackjackResult;

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
}
