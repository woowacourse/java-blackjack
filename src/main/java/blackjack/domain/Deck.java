package blackjack.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Deck {

    private final Queue<Card> values;

    public Deck(Set<Card> values) {
        LinkedList<Card> cards = new LinkedList<>(values);
        Collections.shuffle(cards);

        this.values = cards;
    }

    public Card draw() {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("카드가 모두 소진되었습니다.");
        }
        return values.poll();
    }
}
