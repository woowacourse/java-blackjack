package ui.dto;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public record ParticipantCardsDto(
        CardDto dealerFirstCard,
        List<PlayerDto> players
) {

    public static ParticipantCardsDto from(Players players, Dealer dealer) {
        return new ParticipantCardsDto(CardDto.toDto(dealer.getFirstCard()), toPlayerListDto(players.getPlayers()));
    }

    private static List<PlayerDto> toPlayerListDto(List<Player> players) {
        return players.stream()
                .map(PlayerDto::toDto)
                .toList();
    }
}
