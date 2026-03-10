package dto;

import domain.state.State;
import java.util.List;

public record PlayersCardsDto(
        List<PlayerCardsDto> playerCards
) {
    public static PlayersCardsDto fromStates(List<State> playersState) {
        return new PlayersCardsDto(playersState.stream()
                .map(PlayerCardsDto::fromState)
                .toList());
    }
}

