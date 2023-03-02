package blackjack.domain.model;

import blackjack.domain.vo.Name;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final String playersNames) {
        this.players = makePlayers(playersNames);
    }

    private List<Player> makePlayers(final String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(name -> new Player(new Name(name), new Cards()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
