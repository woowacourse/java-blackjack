package domain;

import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayerCount(players);
        this.players = List.copyOf(players);
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.size() > 4 || players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 4명입니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
