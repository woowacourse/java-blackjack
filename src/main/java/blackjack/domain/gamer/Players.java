package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.deck.Deck;

public class Players {

    private final List<Player> players;
    private int cursor = 0;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public void initialize(Deck deck){
        for (var player : players) {
            player.initialize(deck);
        }
    }

    public boolean hasNext() {
        return cursor < players.size() ;
    }

    public Player getCurrentPlayer() {
        return players.get(cursor);
    }

    public void next() {
        cursor++;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
