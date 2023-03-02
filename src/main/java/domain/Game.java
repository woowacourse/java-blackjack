package domain;

import java.util.List;

public class Game {

    private final List<Player> players;
    private final Deck deck;

    public Game(List<Player> players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void dealCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.drawCard());
            }
        }
    }
}
