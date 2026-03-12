package domain;

import static exception.ErrorMessage.EMPTY_DECK;

import domain.card.Card;
import factory.CardFactory;
import java.util.Collections;
import java.util.List;

public class Deck {
    // todo : cards를 일급컬렉션으로 구현.
    private final List<Card> cards;

    public Deck() {
        this.cards = CardFactory.createDeck();
        shuffle();
    }

    public Card drawCard() {
        validateEmptyDeck();
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return cards;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    private void validateEmptyDeck() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK.getMessage());
        }
    }
}
