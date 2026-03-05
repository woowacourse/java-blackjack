package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import java.util.*;


public class Deck {
    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static List<Card> prepareCards() {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
        Collections.shuffle(cards);

        return cards;
    }

    public int size(){
        return cards.size();
    }

    public Card draw() {
        return cards.removeFirst();
    }
}
