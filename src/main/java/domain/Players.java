package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    List<Player> players = new ArrayList<>();

    public Players(List<Player> players) {
        validatePlayerCount(players);
        validateDuplicatedName(players);
        this.players = players;
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
