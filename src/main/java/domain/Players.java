package domain;

import java.util.HashSet;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        validateDuplicateNames(players);
        validatePlayerCounts(players);
    }

    private void validateDuplicateNames(List<Player> players) {
        int playerCount = (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (players.size() != playerCount) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 중복되지 않아야 합니다.");
        }
    }

    private void validatePlayerCounts(List<Player> players) {
        if (players.isEmpty() || players.size() > 8) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 수는 1명 이상 8명 이하여야 합니다.");
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
