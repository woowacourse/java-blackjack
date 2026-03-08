package domain;

import domain.exception.DeckIsEmptyException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> queue;

    public Deck() {
        queue = new LinkedList<>();
    }

    public void init() {
        for (CardPattern cardPattern : CardPattern.values()) {
            makeCard(cardPattern.getName());
        }
        Collections.shuffle((List<?>) queue);
    }

    public Card draw() {
        if (queue.isEmpty()) {
            throw new DeckIsEmptyException();
        }
        return queue.poll();
    }

    private void makeCard(String cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            queue.add(new Card(cardNumber.getCourt(), cardPattern));
        }
    }
}
