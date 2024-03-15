package domain.gamer;

import java.util.List;

public class Players {
    public static final int MINIMUM_PLAYER_COUNT = 2;
    public static final int MAXIMUM_PLAYER_COUNT = 8;
    private static final String INVALID_PLAYER_COUNT = String.format("플레이어는 %d명에서 %d명까지만 참가 가능합니다.",
            MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT);
    private static final String DUPLICATED_PLAYER_NAME = "플레이어의 이름은 중복될 수 없습니다.";
    private final List<Player> players;

    public Players(final List<String> names) {
        validatePlayerCount(names);
        validateDuplicatedPlayer(names);
        this.players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
    }

    private void validatePlayerCount(final List<String> names) {
        if (names.size() < MINIMUM_PLAYER_COUNT || names.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(INVALID_PLAYER_COUNT);
        }
    }

    private void validateDuplicatedPlayer(final List<String> names) {
        int deduplicatedCount = (int) names.stream().distinct().count();
        if (names.size() != deduplicatedCount) {
            throw new IllegalArgumentException(DUPLICATED_PLAYER_NAME);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
