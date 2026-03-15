package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    private Players() {
        this.players = new ArrayList<>();
    }

    public static Players of() {
        return new Players();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
