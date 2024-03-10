package blackjack.dto;

import blackjack.domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayersDto {

    private final List<PlayerDto> values;

    public PlayersDto(final List<PlayerDto> values) {
        this.values = values;
    }

    public List<PlayerDto> getValues() {
        return values;
    }

    public static PlayersDto toDto(final List<Player> players) {
        final List<PlayerDto> playersResponseDto = new ArrayList<>();
        for (final Player player : players) {
            playersResponseDto.add(PlayerDto.toDto(player));
        }
        return new PlayersDto(playersResponseDto);
    }
}
