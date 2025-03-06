package blackjack.domain.participants;

import java.util.List;
import java.util.function.Consumer;

public class Players {

    private static final int PLAYER_MIN_SIZE = 2;
    private static final int PLAYER_MAX_SIZE = 10;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersSize(players);
        this.players = players;
    }

    private void validatePlayersSize(List<Player> players) {
        if (players.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어 수는 최대 " + PLAYER_MAX_SIZE + "명입니다.");
        }
        if (players.size() < PLAYER_MIN_SIZE) {
            throw new IllegalArgumentException("플레이어 수는 최소 " + PLAYER_MIN_SIZE + "명입니다.");
        }
    }

    public void sendAll(Consumer<Player> consumer) {
        for (Player player : players) {
            consumer.accept(player);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean contains(Player player) {
        return players.contains(player);
    }
}
