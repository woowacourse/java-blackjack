package model;

import model.card.Card;

public class BlackJackGame {

    private final Deck deck;

    public BlackJackGame(final Deck deck) {
        this.deck = deck;
    }

    public Card pickCard() {
        return deck.pick();
    }

}
