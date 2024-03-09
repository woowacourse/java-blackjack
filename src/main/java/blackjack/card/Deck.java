package blackjack.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck createShuffledFullDeck() {
        List<Card> cards = new LinkedList<>();
        for (Shape shape : Shape.values()) {
            cards.addAll(createNumberCardsOf(shape));
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> createNumberCardsOf(Shape shape) {
        List<Card> cards = new ArrayList<>();
        for (Number number : Number.values()) {
            cards.add(new Card(shape, number));
        }
        return cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 덱이 비어있습니다.");
        }
        return cards.poll();
    }
}
