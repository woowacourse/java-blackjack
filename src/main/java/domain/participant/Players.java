package domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final int MAXIMUM_PLAYER_COUNT = 7;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players withNames(List<String> names) {
        validate(names);
        return new Players(names.stream()
            .map(Player::withName)
            .toList());
    }

    private static void validate(List<String> playerNames) {
        validateNull(playerNames);
        validateDuplication(playerNames);
        validateCount(playerNames);
    }

    private static void validateNull(List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 null일 수 없습니다.");
        }
    }

    private static void validateDuplication(List<String> playerNames) {
        Set<String> notDuplicatedNames = new HashSet<>(playerNames);
        if (notDuplicatedNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private static void validateCount(List<String> playerNames) {
        if (playerNames.isEmpty() || playerNames.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(
                String.format("[ERROR] %d~%d명의 플레이어만 허용합니다.", MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
