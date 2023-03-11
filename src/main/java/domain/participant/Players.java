package domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Players {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players create(final List<String> playerNames) {
        validateDuplicateNames(playerNames);
        validatePlayerCount(playerNames);
        List<Player> players = makePlayers(playerNames);
        return new Players(players);
    }

    private static void validateDuplicateNames(final List<String> playerNames) {
        final Set<String> uniqueNames = new HashSet<>(playerNames);
        if (playerNames.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private static void validatePlayerCount(final List<String> playerNames) {
        final int playerCount = playerNames.size();
        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }

    private static List<Player> makePlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(Player::create)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Participant> getPlayers() {
        return List.copyOf(players);
    }
}
