package blackjack.domain.player;

import blackjack.domain.card.Deck;

import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public void initPlayerCards(Deck deck) {
        players.forEach(player -> player.initializeCards(deck));
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }

}
