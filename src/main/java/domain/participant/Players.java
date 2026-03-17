package domain.participant;

import static exception.ErrorMessage.PLAYERS_INVALID_COUNT;
import static exception.ErrorMessage.PLAYER_NAME_IS_DUPLICATE;

import java.util.List;

/**
 * 여러 명의 플레이어를 관리하는 클래스
 */
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
            throw new IllegalArgumentException(PLAYER_NAME_IS_DUPLICATE.getMessage());
        }
    }

    private void validatePlayerCounts(List<Player> players) {
        if (players.isEmpty() || players.size() > 8) {
            throw new IllegalArgumentException(PLAYERS_INVALID_COUNT.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
