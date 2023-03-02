package deck;

import java.util.Stack;

import card.Card;

public class Deck {
    private final Stack<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        this.cards = cardsGenerator.generate();
    }

    public Card drawCard() {
        return cards.pop();
    }
}
