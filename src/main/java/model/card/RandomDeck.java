package model.card;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import model.card.exception.InvalidDeckSizeException;
import model.card.exception.NoMoreCardException;

public class RandomDeck implements Deck {
    public static final int REQUIRED_DECK_SIZE = 52;

    private final Iterator<Card> cards;

    private RandomDeck(List<Card> cards) {
        validateDeckSize(cards);
        this.cards = List.copyOf(cards).iterator();
    }

    public static RandomDeck from(List<Card> cards) {
        List<Card> copied = List.copyOf(cards);

        return new RandomDeck(copied);
    }

    private static void validateDeckSize(List<Card> cards) {
        if (cards.size() != REQUIRED_DECK_SIZE) {
            throw new InvalidDeckSizeException(cards.size());
        }
    }

    @Override
    public Card draw() {
        try {
            return cards.next();
        } catch (NoSuchElementException e) {
            throw new NoMoreCardException(e);
        }
    }
}
