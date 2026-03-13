package dto;

import domain.Result;
import java.util.ArrayList;
import java.util.List;

public record ParticipantGameResultDto(String name, int win, int lose) {

    public static List<ParticipantGameResultDto> from(Result result) {
        List<ParticipantGameResultDto> gameResultDtos = new ArrayList<>();

        gameResultDtos.add(
                new ParticipantGameResultDto(result.getDealerName(), result.getDealerWin(), result.getDealerLose()));
        result.getPlayerResults().forEach((name, isWin) ->
                gameResultDtos.add(new ParticipantGameResultDto(name, isWin ? 1 : 0, isWin ? 0 : 1))
        );
        return gameResultDtos;
    }
}
