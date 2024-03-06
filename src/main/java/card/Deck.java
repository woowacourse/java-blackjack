package card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck() {
        List<Card> initCards = new LinkedList<>();
        // TODO: indent 줄이기, 덱 생성 책임 정하기
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                initCards.add(new Card(shape, number));
            }
        }
        Collections.shuffle(initCards);
        this.cards = new LinkedList<>(initCards);
    }

    public Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 덱이 비어있습니다.");
        }
        return cards.poll();
    }
}
