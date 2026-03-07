package domain;

import domain.card.Card;
import exception.ErrorMessage;
import factory.CardFactory;

import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = CardFactory.createDeck();
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        validateEmptyDeck();
        return cards.removeFirst();
    }

    private void validateEmptyDeck() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ErrorMessage.EMPTY_DECK.getMessage());
        }
    }

    public List<Card> getCards() {
        return cards;
    }

}
