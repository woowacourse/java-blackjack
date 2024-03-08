package domain;

import domain.gamer.Player;
import java.util.List;

public class Players {
    public static final int MINIMUM_PLAYER_COUNT = 2;
    public static final int MAXIMUM_PLAYER_COUNT = 8;
    public static final String INVALID_PLAYER_COUNT = String.format("플레이어는 %d명에서 %d명까지만 참가 가능합니다.",
            MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT);
    public static final String DUPLICATED_PLAYER_NAME = "플레이어의 이름은 중복될 수 없습니다.";
    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayerCount(players);
        validateDuplicatedPlayer(players);
        this.players = List.copyOf(players);
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_COUNT || players.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(INVALID_PLAYER_COUNT);
        }
    }

    private void validateDuplicatedPlayer(final List<Player> players) {
        int deduplicatedCount = (int) players.stream().map(player -> player.getName()).distinct().count();
        if (players.size() != deduplicatedCount) {
            throw new IllegalArgumentException(DUPLICATED_PLAYER_NAME);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
