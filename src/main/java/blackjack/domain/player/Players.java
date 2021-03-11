package blackjack.domain.player;

import blackjack.domain.card.Deck;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
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
}
