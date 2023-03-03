package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Deck deck;
    private final GamePlayer gamePlayer;

    public Game(Deck deck, GamePlayer gamePlayer) {
        this.deck = deck;
        this.gamePlayer = gamePlayer;
    }

    public void init() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < gamePlayer.getPlayers().count()+1; i++) {
            cards.add(deck.draw());
            cards.add(deck.draw());
        }
        gamePlayer.init(cards);
    }

    public boolean isHitPlayerByIndex(int i) {
        return gamePlayer.isHitPlayerByIndex(i);
    }

    public boolean isHitDealer() {
        return gamePlayer.isHitDealer();
    }

    public void giveCardToPlayerByIndex(int i) {
        gamePlayer.giveCardToPlayerByIndex(i, deck.draw());
    }

    public void giveCardToDealer() {
        gamePlayer.giveCardToDealer(deck.draw());
    }


    public Players getPlayers() {
        return gamePlayer.getPlayers();
    }

    public Dealer getDealer() {
        return gamePlayer.getDealer();
    }
}
