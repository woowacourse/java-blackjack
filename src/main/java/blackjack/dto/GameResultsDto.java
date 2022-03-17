package blackjack.dto;

import blackjack.domain.Players;
import java.util.List;
import java.util.stream.Collectors;

public class GameResultsDto {
    private final List<GameResultDto> value;

    public GameResultsDto(List<GameResultDto> value) {
        this.value = value;
    }

    public static GameResultsDto from(Players players) {
        return new GameResultsDto(players.getValue().stream()
                .map(player -> GameResultDto.from(player))
                .collect(Collectors.toList()));
    }

    public List<GameResultDto> getValue() {
        return value;
    }
}
