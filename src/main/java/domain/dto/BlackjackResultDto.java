package domain.dto;

import domain.result.BlackjackResult;

public record BlackjackResultDto(
        int winCount,
        int drawCount,
        int loseCount,
        PlayerResultDto matchResultMap
) {
    public static BlackjackResultDto from(BlackjackResult blackjackResult) {
        return new BlackjackResultDto(
                blackjackResult.getDealerWinningCount(),
                blackjackResult.getDrawCount(),
                blackjackResult.getDealerLoseCount(),
                PlayerResultDto.from(blackjackResult)
        );
    }
}
