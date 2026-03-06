package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        for (String name : names) {
            Player player = new Player(name);
            players.add(player);
        }

        validatePlayerCount(players);
        validateDuplicatedName(players);
    }


    public List<String> getPlayerNames() {
        return players.stream()
                .map(player -> player.name())
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() > 5) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicatedName(List<Player> players) {
        Set<String> namesSet = players.stream()
                .map(player -> player.name())
                .collect(Collectors.toSet());

        if (namesSet.size() != players.size()) {
            throw new IllegalArgumentException();
        }
    }
}
