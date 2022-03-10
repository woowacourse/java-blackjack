package blackjack.domain.player;

import java.util.ArrayList;
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

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public void passTurnUntilHitable() {
        int count = 0;
        while (count < 8 && !getCurrentTurn().isAbleToHit()) {
            passTurnToNext();
            count++;
        }
    }

    public List<Player> toList() {
        return new ArrayList<>(players);
    }
}
