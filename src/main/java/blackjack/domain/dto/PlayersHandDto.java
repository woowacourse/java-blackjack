package blackjack.domain.dto;

import blackjack.domain.gamer.Players;
import java.util.List;

public record PlayersHandDto(List<PlayerHandDto> playersHandDto) {

    public static PlayersHandDto fromPlayers(Players players) {
        List<PlayerHandDto> playerHandDtos = players.getPlayers().stream()
                .map(PlayerHandDto::fromPlayer)
                .toList();

        return new PlayersHandDto(playerHandDtos);
    }
}
