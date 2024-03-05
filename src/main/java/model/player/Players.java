package model.player;

import java.util.List;
import model.card.Cards;

public class Players {

    private final List<Player> players;
    private Cards cards;

    public Players(List<Player> players, Cards cards) {
        this.players = players;
        this.cards = cards;
    }

    public void offerCardToPlayers(int cardCount) {
        for(Player player : players) {
            player.addCards(cards.selectRandomCards(cardCount));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
