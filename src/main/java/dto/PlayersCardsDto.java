package dto;

import domain.participants.Player;
import java.util.List;

public record PlayersCardsDto(
        List<PlayerCardsDto> playerCards
) {
    public static PlayersCardsDto fromPlayers(List<Player> players) {
        return new PlayersCardsDto(players.stream()
                .map(PlayerCardsDto::fromParticipant)
                .toList());
    }
}

