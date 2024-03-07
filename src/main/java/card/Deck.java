package card;

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
        List<Card> initCards = new LinkedList<>();
        // TODO: indent 줄이기
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                initCards.add(new Card(shape, number));
            }
        }
        Collections.shuffle(initCards);
        return new Deck(initCards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 덱이 비어있습니다.");
        }
        return cards.poll();
    }
}
