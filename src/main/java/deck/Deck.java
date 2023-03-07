package deck;

import java.util.Stack;

import card.Card;

public class Deck {
    private final Stack<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        this.cards = cardsGenerator.generate();
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드 덱이 비어있습니다.");
        }
        return cards.pop();
    }
}
