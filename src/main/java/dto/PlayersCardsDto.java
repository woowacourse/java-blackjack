package dto;

import domain.participants.Player;
import java.util.List;

public record PlayersCardsDto(
        List<PlayerCardsDto> playerCards
) {
    public static PlayersCardsDto fromStates(List<Player> players) {
        return new PlayersCardsDto(players.stream()
                .map(PlayerCardsDto::fromParticipant)
                .toList());
    }
}

