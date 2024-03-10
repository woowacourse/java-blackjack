package view.dto.participant;

import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;

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

    public Players toDomain() {
        List<Player> players = players().stream()
                                        .map(playerDto -> new Player(new Name(playerDto.name())))
                                        .toList();
        return new Players(players);
    }

}
