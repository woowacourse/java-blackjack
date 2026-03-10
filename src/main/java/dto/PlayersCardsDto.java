package dto;

import domain.state.State;
import java.util.List;

public record PlayersCardsDto(
        List<PlayerCardsDto> playerCards
) {
    public static PlayersCardsDto fromEntities(List<State> playersState) {
        return new PlayersCardsDto(playersState.stream()
                .map(PlayerCardsDto::fromEntity)
                .toList());
    }
}

