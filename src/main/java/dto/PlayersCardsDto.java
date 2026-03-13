package dto;

import domain.participants.Participant;
import java.util.List;

public record PlayersCardsDto(
        List<PlayerCardsDto> playerCards
) {
    public static PlayersCardsDto fromPlayers(List<Participant> players) {
        return new PlayersCardsDto(players.stream()
                .map(PlayerCardsDto::fromParticipant)
                .toList());
    }
}

