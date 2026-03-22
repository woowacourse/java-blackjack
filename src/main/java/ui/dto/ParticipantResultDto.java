package ui.dto;

import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;

public record ParticipantResultDto(
        List<CardDto> dealerCards,
        int dealerScore,
        List<PlayerDto> players
) {
    public static ParticipantResultDto toDto(Players players, Dealer dealer) {
        return new ParticipantResultDto(CardDto.toDtoList(dealer.getState().hand().getCards()),
                dealer.totalSum().value(), PlayerDto.toDtoList(players.getPlayers()));
    }
}
