package domain.participant;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.hasSameName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다."));
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}
