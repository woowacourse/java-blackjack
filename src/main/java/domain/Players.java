package domain;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    private static final int MIN_PLAYERS_SIZE = 1;
    private static final int MAX_PLAYERS_NUMBER = 4;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(final List<Player> players) {
        if (players.size() < MIN_PLAYERS_SIZE || players.size() > MAX_PLAYERS_NUMBER) {
            throw new IllegalArgumentException("플레이어의 수는 최소 1명, 최대 4명입니다.");
        }
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}
