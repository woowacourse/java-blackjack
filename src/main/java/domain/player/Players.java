package domain.player;

import java.util.List;

public class Players {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;

    private Players(final List<String> playerNames) {
        validatePlayerCount(playerNames);
    }

    public static Players create(final List<String> playerNames) {
        return new Players(playerNames);
    }

    private void validatePlayerCount(final List<String> playerNames) {
        int playerCount = playerNames.size();
        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }
}
