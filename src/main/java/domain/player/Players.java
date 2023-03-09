package domain.player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<Name> names, final List<Amount> amounts) {
        this.players = generatePlayers(names, amounts);
    }

    private List<Player> generatePlayers(final List<Name> names, final List<Amount> amounts) {
        final List<Player> players = new ArrayList<>();

        for (int i = 0; i < amounts.size(); i++) {
            players.add(new Player(names.get(i), amounts.get(i)));
        }

        return players;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Player getPlayer(final Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
