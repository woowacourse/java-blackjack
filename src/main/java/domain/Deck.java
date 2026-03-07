package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import java.util.*;


public class Deck {
    public static final int INITIAL_CARDS_COUNT = 2;
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static List<Card> createDeck() {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            createBySuit(rank, cards);
        }
        Collections.shuffle(cards);

        return cards;
    }

    private static void createBySuit(Rank rank, List<Card> cards) {
        for (Suit suit : Suit.values()) {
            Card card = new Card(rank, suit);
            cards.add(card);
        }
    }

    public int size(){
        return cards.size();
    }

    public Card draw() {
        return cards.removeFirst();
    }

    public List<Card> drawInitialCards() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            cards.add(draw());
        }

        return cards;
    }
}
