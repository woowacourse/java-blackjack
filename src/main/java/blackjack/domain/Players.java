package blackjack.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Players {

    private final Queue<Player> players;

    public Players(List<Player> players) {
        this.players = new LinkedList<>(players);
    }

    public Player getCurrentTurn() {
        return players.peek();
    }

    public void passTurnToNext() {
        players.offer(players.poll());
    }
}
