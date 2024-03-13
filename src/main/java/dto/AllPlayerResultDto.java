package dto;

import domain.gamer.Player;
import domain.gamer.Players;
import java.util.ArrayList;
import java.util.List;

public record AllPlayerResultDto(List<PlayerCardStateDto> playersCardStateDto, List<Integer> playersScore) {

    public static AllPlayerResultDto makeAllPlayerResultDto(Players players) {
        List<PlayerCardStateDto> allPlayerResult = new ArrayList<>();
        List<Integer> allPlayerScore = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            allPlayerResult.add(PlayerCardStateDto.makePlayerCardState(player));
            allPlayerScore.add(player.getMaxGameScore());
        }
        return new AllPlayerResultDto(allPlayerResult, allPlayerScore);
    }
}
