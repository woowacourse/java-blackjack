package domain.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {
    private static final String ERROR_DUPLICATE_NAME = "플레이어 이름은 중복될 수 없습니다.";
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public static Players of(List<Player> players) {
        validate(players);
        return new Players(players);
    }

    private static void validate(List<Player> players) {
        validateUniqueName(players);
    }

    private static void validateUniqueName(List<Player> players) {
        if (hasDuplicateName(players)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    private static boolean hasDuplicateName(List<Player> players) {
        Set<Player> uniquePlayers = new HashSet<>(players);

        return players.size() != uniquePlayers.size();
    }
}
