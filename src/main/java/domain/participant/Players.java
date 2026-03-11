package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int PLAYER_THRESHOLD = 5;
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        for (String name : names) {
            Player player = new Player(name);
            players.add(player);
        }

        validatePlayerCount(players);
        validateDuplicatedName(players);
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() > PLAYER_THRESHOLD) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicatedName(List<Player> players) {
        int playersNameDistinctSize = Math.toIntExact(players.stream()
                .map(Participant::name)
                .distinct()
                .count());

        if (playersNameDistinctSize != players.size()) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Participant::name)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
