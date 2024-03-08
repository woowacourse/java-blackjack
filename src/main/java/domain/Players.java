package domain;

import domain.gamer.Player;
import java.util.List;

public class Players {
    public static final int MINIMUM_PLAYER_COUNT = 2;
    public static final int MAXIMUM_PLAYER_COUNT = 8;

    public static final String INVALID_PLAYER_COUNT = String.format("플레이어는 %d명에서 %d명까지만 참가 가능합니다.",
            MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT);
    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayerCount(players);
        this.players = List.copyOf(players);
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_COUNT || players.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(INVALID_PLAYER_COUNT);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
