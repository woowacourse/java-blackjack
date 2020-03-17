package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void receiveDistributedCardsAllPlayers(CardDeck cardDeck) {
        players.forEach(player -> player.receiveDistributedCards(cardDeck));
    }

}
