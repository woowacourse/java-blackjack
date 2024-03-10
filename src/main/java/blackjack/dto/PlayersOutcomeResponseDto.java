package blackjack.dto;

import blackjack.domain.Outcome;
import blackjack.domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayersOutcomeResponseDto {

    private final List<PlayerOutcomeResponseDto> values;

    public PlayersOutcomeResponseDto(List<PlayerOutcomeResponseDto> values) {
        this.values = values;
    }

    public List<PlayerOutcomeResponseDto> getValues() {
        return values;
    }

    public static PlayersOutcomeResponseDto toDto(final Map<Player, Outcome> playerOutcomeMap) {
        final List<PlayerOutcomeResponseDto> playersOutcomeResponseDto = new ArrayList<>();
        for (final Player player : playerOutcomeMap.keySet()) {
            playersOutcomeResponseDto.add(
                    PlayerOutcomeResponseDto.toDto(player.getName(), playerOutcomeMap.get(player))
            );
        }
        return new PlayersOutcomeResponseDto(playersOutcomeResponseDto);
    }
}
