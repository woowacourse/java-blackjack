package domain.player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<String> playerNames) {
        this.players = generatePlayers(playerNames);
    }

    private List<Player> generatePlayers(final List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        playerNames.forEach(
                name -> players.add(new Player(name))
        );

        return players;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Player getPlayer(final String name) {
        return players.stream()
                .filter(player -> player.name().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
