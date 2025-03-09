package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> deck;

    public static Deck of() {
        List<Card> deck = new ArrayList<>();
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                Card card = new Card(rank, cardSuit);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public Card pick() {
        validateDeckEmpty();
        Card card = deck.getFirst();
        deck.remove(card);
        return card;
    }

    private void validateDeckEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("주어진 모든 카드들을 소진하였습니다");
        }
    }
}
