package blackjack.domain.card;

import java.util.Deque;

import blackjack.domain.card.deckstrategy.DeckStrategy;

public class CardDeck {

    private final Deque<Card> deck;

    public CardDeck(DeckStrategy deckStrategy) {
        this.deck = deckStrategy.create();
    }

    public Card drawCard() {
        return deck.removeLast();
    }
}
