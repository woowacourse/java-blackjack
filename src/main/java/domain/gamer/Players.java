package domain.gamer;

import exception.DuplicatedNameException;
import exception.PlayerCountException;
import java.util.List;

public class Players {
    public static final int MINIMUM_PLAYER_COUNT = 2;
    public static final int MAXIMUM_PLAYER_COUNT = 8;
    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayerCount(players);
        validateDuplicatedPlayer(players);
        this.players = List.copyOf(players);
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_COUNT || players.size() > MAXIMUM_PLAYER_COUNT) {

            throw new PlayerCountException(PlayerCountException.INVALID_PLAYER_COUNT);
        }
    }

    private void validateDuplicatedPlayer(final List<Player> players) {
        int deduplicatedCount = (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (players.size() != deduplicatedCount) {
            throw new DuplicatedNameException(DuplicatedNameException.DUPLICATED_PLAYER_NAME);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
