package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        setUp();
    }

    private void setUp() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }

        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }
}
