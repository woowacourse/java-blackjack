package domain.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 4;
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names) {
        validate(names);
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validate(final List<String> names) {
        validateDuplication(names);
        validateSize(names);
    }

    private static void validateDuplication(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private static void validateSize(final List<String> names) {
        if (names.size() < PLAYER_MIN_SIZE || names.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("플레이어 수는 %d명 이상, %d명 이하여야 합니다.", PLAYER_MIN_SIZE, PLAYER_MAX_SIZE));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
