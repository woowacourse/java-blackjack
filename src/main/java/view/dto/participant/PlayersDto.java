package view.dto.participant;

import java.util.List;

import domain.participant.Players;

public record PlayersDto(List<PlayerDto> dtos) {
    public PlayersDto(final List<PlayerDto> dtos) {
        this.dtos = List.copyOf(dtos);
    }

    public PlayersDto(final Players players) {
        this(players.getPlayers()
                .stream()
                .map(PlayerDto::new)
                .toList());
    }
}
