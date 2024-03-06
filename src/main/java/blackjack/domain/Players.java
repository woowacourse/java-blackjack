package blackjack.domain;

import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayersDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        final List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(Player.from(new Name(name)));
        }
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<Name> getNames() {
        final List<Name> names = new ArrayList<>();
        for (final Player player : players) {
            names.add(player.getName());
        }
        return names;
    }

    public PlayersDto toDto() {
        final List<PlayerDto> playerDtos = new ArrayList<>();
        for (final Player player : players) {
            playerDtos.add(player.toDto());
        }
        return new PlayersDto(playerDtos);
    }
}
