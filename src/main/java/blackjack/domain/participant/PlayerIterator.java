package blackjack.domain.participant;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerIterator {
    private final Queue<Player> players;

    public PlayerIterator(Players players) {
        this.players = new LinkedList<>(players.getValues());
    }

    public boolean hasNext() {
        return !players.isEmpty();
    }

    public void increaseOrderByActionAndHand(PlayerAction playerAction) {
        if (playerAction.equals(PlayerAction.STAND) || !getPlayer().canHit()) {
            players.poll();
        }
    }

    public Player getPlayer() {
        return players.peek();
    }
}
