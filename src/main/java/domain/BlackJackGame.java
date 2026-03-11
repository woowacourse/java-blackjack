package domain;

import java.util.List;

public class BlackJackGame {
    private final Deck deck;
    private final List<Player> players;

    public BlackJackGame(Deck deck, List<Player> players) {
        this.deck = deck;
        this.players = players;
    }


}
