package blackjack.domain.user;

import java.util.List;

public class Players {

    private static final int MAX_PLAYER_COUNT = 13;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validatePlayers(players);
        this.players = players;
    }

    private void validatePlayers(final List<Player> players) {
        validatePlayerCount(players);
        validateDuplicatedNames(players);
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.isEmpty() || players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("플레이어들은 0명 이상 13명 이하여야 합니다.");
        }
    }

    private void validateDuplicatedNames(final List<Player> players) {

        final long count = players.stream().map(User::getName).distinct().count();

        if (count != players.size()) {
            throw new IllegalArgumentException("플레이어들의 이름은 고유하여야 합니다.");
        }
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
