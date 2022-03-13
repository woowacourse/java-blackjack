package blackjack.domain.card;

import java.util.Stack;

import blackjack.domain.card.deckstrategy.DeckStrategy;

public class CardDeck {

    private final Stack<Card> deck;

    public CardDeck(DeckStrategy deckStrategy) {
        this.deck = deckStrategy.create();
    }

    public Card drawCard() {
        return deck.pop();
    }
}
