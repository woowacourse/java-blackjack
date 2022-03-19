package blackjack.domain.game;

import java.util.Deque;

import blackjack.domain.card.Card;
import blackjack.domain.card.deckstrategy.DeckStrategy;

public final class CardDeck {

    private final Deque<Card> deck;

    public CardDeck(DeckStrategy deckStrategy) {
        this.deck = deckStrategy.create();
    }

    Card drawCard() {
        return deck.removeLast();
    }
}
