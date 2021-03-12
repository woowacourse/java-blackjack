package blackjack.domain.player;

import blackjack.domain.card.Deck;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Players {

    private final List<Player> players;
    private final Queue<Player> currentPlayers;

    public Players(List<Player> players) {
        this.players = players;
        this.currentPlayers = new LinkedList<>(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void initialDraw(Deck deck) {
        players.forEach(player -> player.initialDraw(deck));
    }

    public void calculateGameResult(Dealer dealer) {
        players.forEach(player -> player.calculateGameResult(dealer));
    }

    public boolean isNotEnd() {
        return currentPlayers.size() != 0;
    }

    public Player getCurrentPlayer() {
        return currentPlayers.peek();
    }

    public boolean isAllFinished() {
        currentPlayers.removeIf(Participant::isFinished);
        return currentPlayers.size() == 0;
    }


}
