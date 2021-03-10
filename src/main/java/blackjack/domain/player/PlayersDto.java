package blackjack.domain.player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {

    private final List<PlayerDto> playerDtos;

    public PlayersDto(List<PlayerDto> gamerDtos) {
        this.playerDtos = gamerDtos;
    }

    public static PlayersDto from(List<? extends Player> players) {
        List<PlayerDto> playerDtos = players.stream()
            .map(player -> PlayerDto.from(player))
            .collect(Collectors.toList());
        return new PlayersDto(playerDtos);
    }

    public List<PlayerDto> getPlayerDtos() {
        return Collections.unmodifiableList(playerDtos);
    }
}
