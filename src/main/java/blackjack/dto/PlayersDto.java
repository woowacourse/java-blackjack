package blackjack.dto;

import blackjack.domain.Players;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {
    private final List<GamerDto> value;

    public PlayersDto(List<GamerDto> value) {
        this.value = value;
    }

    public static PlayersDto from(Players players) {
        return new PlayersDto(players.getValue().stream()
                .map(GamerDto::from)
                .collect(Collectors.toList()));
    }

    public List<GamerDto> getValue() {
        return value;
    }
}
