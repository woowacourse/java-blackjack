package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        this.cards = cardsGenerator.generate();
    }

    public Card drawCard() {
        return cards.pop();
    }
}
