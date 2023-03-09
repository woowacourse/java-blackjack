package domain.player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<String> playerNames, final List<Integer> amounts) {
        this.players = generatePlayers(playerNames, amounts);
    }

    private List<Player> generatePlayers(final List<String> playerNames, final List<Integer> amounts) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < amounts.size(); i++) {
            players.add(new Player(playerNames.get(i), amounts.get(i)));
        }

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
