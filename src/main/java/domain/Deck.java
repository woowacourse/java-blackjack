package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;

import java.util.*;


public class Deck {
    private Cards cards = new Cards();

    public Deck() {
        setUp();
    }

    private void setUp() {
        prepareCards(cards);
        Collections.shuffle(List.of(cards));
    }

    private static void prepareCards(Cards cards) {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.draw();
    }
}
