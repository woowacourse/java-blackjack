package blackjack.controller;

import blackjack.domain.deck.Deck;

public class BlackjackController {

    public void play() {
        Deck deck = initDeck();
    }

    private Deck initDeck() {
        Deck deck = new Deck(Deck.initCards());
        deck.shuffle();
        return deck;
    }
}
