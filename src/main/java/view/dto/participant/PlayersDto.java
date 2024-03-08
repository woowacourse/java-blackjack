package view.dto.participant;

import java.util.List;

import domain.participant.Players;

public record PlayersDto(List<PlayerDto> players) {
    public PlayersDto(final List<PlayerDto> players) {
        this.players = List.copyOf(players);
    }

    public PlayersDto(final Players players) {
        this(players.getPlayers()
                .stream()
                .map(PlayerDto::new)
                .toList());
    }
}
