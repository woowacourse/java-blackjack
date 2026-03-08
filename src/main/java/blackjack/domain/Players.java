package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public void receiveCards(Deck deck) {
        for (Player player : players) {
            player.receiveCards(deck.drawSecondTimes());
        }
    }

    public int count() {
        return this.players.size();
    }

    public boolean canHit(int playerIndex) {
        return this.players.get(playerIndex).canHit();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void hitPlayer(int index, TrumpCard card) {
        players.get(index).receiveCard(card);
    }

    public Player playerAt(int index){
        return players.get(index);
    }
}
