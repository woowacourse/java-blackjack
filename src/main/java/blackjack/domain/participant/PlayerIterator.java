package blackjack.domain.participant;

import java.util.List;

public class PlayerIterator {
    private int order = 0;
    private final List<Player> players;

    public PlayerIterator(Players players) {
        this.players = players.getValues();
    }

    public boolean hasNext() {
        return order < players.size();
    }

    public Player getPlayer() {
        return players.get(order);
    }

    public void update(boolean isPlayerWantHit) {
        if (!isPlayerWantHit || !getPlayer().canHit()) {
            order++;
        }
    }
}
