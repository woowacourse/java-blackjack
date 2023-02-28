package model;

import model.card.Card;

public class BlackJackGame {

    private final Deck deck;

    public BlackJackGame(final Deck deck) {
        this.deck = deck;
    }

    public void divideCard(final Result result, final Player player) {
        final Card card = deck.pick();
        result.addCard(player, card);
    }
}
