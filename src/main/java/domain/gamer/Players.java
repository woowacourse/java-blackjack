package domain.gamer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MAX_PLAYER_COUNT = 4;
    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayerCount(players);
        validateDuplicateName(players);
        this.players = List.copyOf(players);
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.size() > MAX_PLAYER_COUNT || players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 4명입니다.");
        }
    }

    private void validateDuplicateName(final List<Player> players) {
        final Set<Player> names = new HashSet<>(players);

        if (names.size() == players.size()) {
            return;
        }

        throw new IllegalArgumentException("닉네임은 중복될 수 없습니다.");
    }

    public List<Player> getPlayers() {
        return players;
    }
}
