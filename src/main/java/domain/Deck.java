package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        List<Card> beforeShuffledCards = generateDeck();
        this.cards = shuffleCards(beforeShuffledCards);
    }

    public Card distributeCard() {
        Card card = cards.removeFirst();
        return card;
    }

    private List<Card> generateDeck() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(shape, number));
            }
        }
        return cards;
    }

    private Deque<Card> shuffleCards(List<Card> cards) {
        List<Card> shuffled = new ArrayList<>(cards);
        Collections.shuffle(shuffled);
        return new ArrayDeque<>(shuffled);
    }
}
