package domain.deck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        cards = initCards();
    }

    private Deque<Card> initCards() {
        Deque<Card> cards = new ArrayDeque<>();

        for (Suit suit : Suit.values()) {
            pushCards(cards, suit);
        }

        return cards;
    }

    public void shuffleDeck() {
        List<Card> list = new ArrayList<>(cards);
        Collections.shuffle(list);
        cards.clear();
        cards.addAll(list);
    }

    private void pushCards(final Deque<Card> cards, final Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.push(new Card(suit, rank));
        }
    }

    public Card popCard() {
        return cards.pop();
    }
}
