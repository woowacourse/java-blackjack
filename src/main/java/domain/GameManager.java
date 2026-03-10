package domain;

import java.util.List;

public class GameManager {
    private static final Integer MAX_PLAYER = 8;

    public static void validatePlayerNamesSize(List<String> playerNames) {
        validateMinimumPlayers(playerNames);
        validateMaximumPlayers(playerNames);
    }

    private static void validateMaximumPlayers(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private static void validateMinimumPlayers(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상이어야 합니다.");
        }
    }
}
