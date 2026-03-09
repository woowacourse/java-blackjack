package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RandomDeck implements Deck {
    private static final int DECK_SIZE = 52;

    private final Iterator<Card> cards;

    private RandomDeck(List<Card> cards) {
        validateDeckSize(cards);
        this.cards = List.copyOf(cards).iterator();
    }

    public static RandomDeck of(Suit[] suits, Rank[] ranks) {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                cards.add(Card.of(suit, rank));
            }
        }

        Collections.shuffle(cards);

        return new RandomDeck(List.copyOf(cards));
    }

    public static RandomDeck from(List<Card> cards) {
        return new RandomDeck(cards);
    }

    private static void validateDeckSize(List<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new IllegalArgumentException("덱은 " + DECK_SIZE + "개의 카드를 가져야 합니다.");
        }
    }

    @Override
    public Card draw() {
        try {
            return cards.next();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("덱이 비었습니다.", e);
        }
    }
}
