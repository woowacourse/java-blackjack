package blackjack.dto;

import blackjack.domain.gamer.Players;
import java.util.List;

public record GamersHandDto(List<GamerHandDto> gamersHandDto) {

    public static GamersHandDto fromPlayers(Players players) {
        List<GamerHandDto> gamerHandDtos = players.getPlayers().stream()
                .map(GamerHandDto::fromBlackjackGamer)
                .toList();

        return new GamersHandDto(gamerHandDtos);
    }
}
