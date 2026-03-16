package dto;

import domain.Result;
import java.util.ArrayList;
import java.util.List;

public record ParticipantGameResultDto(String name, int profit) {

    public static List<ParticipantGameResultDto> from(Result result) {
        List<ParticipantGameResultDto> gameResultDtos = new ArrayList<>();

        gameResultDtos.add(
                new ParticipantGameResultDto(result.getDealerName(), result.getDealerProfit()));

        result.getPlayerResults().forEach((player, matchResult) ->
                gameResultDtos.add(new ParticipantGameResultDto(
                        player.getName(),
                        result.getPlayerProfit(player)
                ))
        );
        return gameResultDtos;
    }
}
