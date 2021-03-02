package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(String value) {
        this.players = splitPlayers(value);
    }

    private List<Player> splitPlayers(String value) {
        List<Player> splitPlayers = new ArrayList<>();
        for (String name : value.split(",")) {
            Player player = Player.create(name);
            splitPlayers.add(player);
        }
        return splitPlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    public String getNames() {
        return String.join(", ", players.stream().map(Player::getName).collect(Collectors.toList()));
    }
}
