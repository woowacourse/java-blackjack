package blackjack.model.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Deck {
    private static final String WARNING_NOT_ENOUGH_CARDS = "[ERROR] 덱에 카드가 부족합니다";

    private final Queue<Card> deck = initialize();

    public Deck() {
        shuffle();
    }

    private Queue<Card> initialize() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
        return new ArrayDeque<>(deck);
    }

    private void shuffle() {
        List<Card> cards = new ArrayList<>(deck);
        Collections.shuffle(cards);
        deck.clear();
        deck.addAll(cards);
    }

    public List<Card> drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        if (deck.isEmpty() || deck.size() < count) {
            throw new IllegalStateException(WARNING_NOT_ENOUGH_CARDS);
        }
        for (int i = 0; i < count; i++) {
            cards.add(deck.poll());
        }
        return cards;
    }
}
