package domain.participant;

import java.util.List;

public class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;

    private Participants(final List<String> playerNames) {
        validatePlayerCount(playerNames);
    }

    public static Participants create(final List<String> playerNames) {
        return new Participants(playerNames);
    }

    private void validatePlayerCount(final List<String> playerNames) {
        int playerCount = playerNames.size();
        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }
}
