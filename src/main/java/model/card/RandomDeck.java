package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import model.card.exception.InvalidDeckSizeException;
import model.card.exception.NoMoreCardException;

public class RandomDeck implements Deck {
    public static final int REQUIRED_DECK_SIZE = 52;

    private final Iterator<Card> cards;

    private RandomDeck(List<Card> cards) {
        List<Card> mutableCopied = new ArrayList<>(cards);

        validateDeckSize(mutableCopied);
        Collections.shuffle(mutableCopied);

        this.cards = List.copyOf(mutableCopied).iterator();
    }

    public static RandomDeck from(List<Card> cards) {
        return new RandomDeck(cards);
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
