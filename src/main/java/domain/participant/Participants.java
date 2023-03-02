package domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;

    private Participants(final List<String> playerNames) {
        validateDuplicateNames(playerNames);
        validatePlayerCount(playerNames);
    }

    public static Participants create(final List<String> playerNames) {
        return new Participants(playerNames);
    }

    private void validateDuplicateNames(final List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);
        if (playerNames.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerCount(final List<String> playerNames) {
        int playerCount = playerNames.size();
        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }
}
