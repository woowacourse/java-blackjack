package blackjack.dto;

import blackjack.domain.Outcome;
import blackjack.domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayersOutcomeDto {

    private final List<PlayerOutcomeDto> values;

    public PlayersOutcomeDto(List<PlayerOutcomeDto> values) {
        this.values = values;
    }

    public List<PlayerOutcomeDto> getValues() {
        return values;
    }

    public static PlayersOutcomeDto toDto(final Map<Player, Outcome> playerOutcomeMap) {
        final List<PlayerOutcomeDto> playersOutcomeResponseDto = new ArrayList<>();
        for (final Player player : playerOutcomeMap.keySet()) {
            playersOutcomeResponseDto.add(
                    PlayerOutcomeDto.toDto(player.getName(), playerOutcomeMap.get(player))
            );
        }
        return new PlayersOutcomeDto(playersOutcomeResponseDto);
    }
}
