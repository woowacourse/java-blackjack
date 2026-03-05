package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final Deck deck;
    private final List<Card> cards;

    public Dealer(Deck deck) {
        this.deck = deck;
        this.cards = new ArrayList<>();
    }

    public void bring(Card card) {
        cards.add(card);
    }

    public Card bringCard() {
        return deck.bringTopCard();
    }

}
