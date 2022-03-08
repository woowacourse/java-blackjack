package blackjack.domain.human;

import java.util.List;

public class Players {
    private final List<Player> players;


    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public int size() {
        return players.size();
    }
}
