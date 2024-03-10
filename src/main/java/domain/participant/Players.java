package domain.participant;

import java.util.List;
import java.util.function.Consumer;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateSize(players);
        this.players = List.copyOf(players);
    }

    private void validateSize(final List<Player> players) {
        if (players.isEmpty() || players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("플레이어의 수는 최소 %d명 최대 %d명입니다 : 현재 %d명"
                    .formatted(MIN_SIZE, MAX_SIZE, players.size()));
        }
    }

    public void forEach(final Consumer<Player> action) {
        players.forEach(action);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
