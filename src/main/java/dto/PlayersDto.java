package dto;

import domain.participant.Players;

import java.util.List;

public record PlayersDto(
        List<PlayerDto> playersDto
) {
    public static PlayersDto from(Players players) {
        return new PlayersDto(players.getPlayers().stream()
                .map(PlayerDto::from)
                .toList());
    }
}
