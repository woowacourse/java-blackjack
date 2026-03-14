package dto;

import domain.MatchResult;
import domain.Result;
import java.util.ArrayList;
import java.util.List;

public record ParticipantGameResultDto(String name, int win, int lose, int draw) {

    public static List<ParticipantGameResultDto> from(Result result) {
        List<ParticipantGameResultDto> gameResultDtos = new ArrayList<>();

        gameResultDtos.add(
                new ParticipantGameResultDto(result.getDealerName(), result.getDealerWin(), result.getDealerLose(),
                        result.getDealerDraw()));

        result.getPlayerResults().forEach((name, matchResult) ->
                gameResultDtos.add(new ParticipantGameResultDto(
                        name,
                        matchResult == MatchResult.WIN ? 1 : 0,
                        matchResult == MatchResult.LOSE ? 1 : 0,
                        matchResult == MatchResult.DRAW ? 1 : 0
                ))
        );
        return gameResultDtos;
    }
}
