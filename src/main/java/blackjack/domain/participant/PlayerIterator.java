package blackjack.domain.participant;

import java.util.List;

public class PlayerIterator {
    private final List<Player> players;
    private int order = 0;

    public PlayerIterator(Players players) {
        this.players = players.getValues();
    }

    public boolean hasNext() {
        return order < players.size();
    }

    public Player getPlayer() {
        return players.get(order);
    }

    public void increaseOrderByActionAndHand(PlayerAction playerAction) {
        if (playerAction.equals(PlayerAction.STAND) || !getPlayer().canHit()) {
            order++;
        }
    }
}
