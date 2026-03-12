package domain;

import domain.card.Card;
import exception.ErrorMessage;
import factory.CardFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card drawCard() {
        validateEmptyDeck();
        return cards.removeFirst();
    }

    public int getCardsSize(){
        return cards.size();
    }

    private void validateEmptyDeck() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ErrorMessage.EMPTY_DECK.getMessage());
        }
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
