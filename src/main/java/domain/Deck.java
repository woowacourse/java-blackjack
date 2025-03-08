package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck implements CardProvider {

    private final Deque<Card> deck = new ArrayDeque<>();

    public Deck() {
        List<Card> temp = new ArrayList<>();
        for (Suit suit : Suit.getAllSuits()) {
            addAllRanks(suit, temp);
        }

        Collections.shuffle(temp);
        deck.addAll(temp);
    }

    private static void addAllRanks(Suit suit, List<Card> temp) {
        for (Rank rank : Rank.getAllRanks()) {
            temp.add(new Card(suit, rank));
        }
    }

    public List<Card> provideCards(int size) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cards.add(deck.pollFirst());
        }
        return cards;
    }
}
