package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {

    private final List<PlayerDto> value;

    public PlayersDto(final List<PlayerDto> value) {
        this.value = value;
    }

    public static PlayersDto from(final List<Player> players) {
        return new PlayersDto(players.stream()
            .map(PlayerDto::from)
            .collect(Collectors.toList()));
    }

    public List<PlayerDto> getValue() {
        return this.value;
    }
}
