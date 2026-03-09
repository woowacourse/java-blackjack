package domain.dto;

import domain.BlackjackResult;
import domain.MatchCase;

import java.util.Map;

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
