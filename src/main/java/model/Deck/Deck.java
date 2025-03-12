package model.Deck;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class Deck {
    private final List<Card> deck;

    public static Deck of() {
        List<Card> deck = new LinkedList<>();
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                Card card = new Card(rank, cardSuit);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    public Deck(final List<Card> deck) {
        this.deck = deck;
    }

    public Card pick() {
        validateDeckEmpty();
        Card removingCard = deck.removeFirst();
        return removingCard;
    }

    private void validateDeckEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("주어진 모든 카드들을 소진하였습니다");
        }
    }
}
