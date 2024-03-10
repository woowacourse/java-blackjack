package blackjack.domain.dto;

import blackjack.domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayersResponseDto {

    private final List<PlayerResponseDto> values;

    public PlayersResponseDto(final List<PlayerResponseDto> values) {
        this.values = values;
    }

    public List<PlayerResponseDto> getValues() {
        return values;
    }

    public static PlayersResponseDto toDto(final List<Player> players) {
        final List<PlayerResponseDto> playersResponseDto = new ArrayList<>();
        for (final Player player : players) {
            playersResponseDto.add(PlayerResponseDto.toDto(player));
        }
        return new PlayersResponseDto(playersResponseDto);
    }
}
