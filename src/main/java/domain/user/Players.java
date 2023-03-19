package domain.user;

import domain.CardHand;
import java.util.List;

public class Players {
    private final List<Player> players;
    private int index;

    public Players(List<Player> players) {
        this.players = players;
        this.index = 0;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void nextPlayer() {
        index++;
    }

    public Player getCurrnetPlayer() {
        try {
            return players.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean isContinuable() {
        return index < players.size();
    }

    public boolean currentPlayerCanAdd() {
        return getCurrnetPlayer().canAdd();
    }

    public CardHand currentPlayerCardHand() {
        return getCurrnetPlayer().cardHand;
    }
}
