package team.blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
    }

     public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }
}
