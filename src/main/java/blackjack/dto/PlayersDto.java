package blackjack.dto;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {

    private final List<PlayerDto> value;

    public PlayersDto(final List<PlayerDto> value) {
        this.value = value;
    }

    public static PlayersDto from(final Players players) {
        return new PlayersDto(players.getValue()
            .stream()
            .map(PlayerDto::from)
            .collect(Collectors.toList()));
    }

    public static PlayersDto getGamblersFrom(final Players players) {
        final List<Player> gamblers = players.getGamblers();
        return new PlayersDto(gamblers.stream()
            .map(PlayerDto::from)
            .collect(Collectors.toList()));
    }

    public List<PlayerDto> getValue() {
        return this.value;
    }
}
