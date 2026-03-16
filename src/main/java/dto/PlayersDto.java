package dto;

import domain.participant.Player;

import java.util.List;

public class PlayersDto {

    private final List<PlayerDto> players;

    public PlayersDto(List<Player> players) {
        this.players = players.stream()
                .map(PlayerDto::new)
                .toList();
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }
}