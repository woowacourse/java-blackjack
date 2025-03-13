package blackjack.domain.gamer;

import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
