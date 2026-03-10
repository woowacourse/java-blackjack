package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class FixedDeck implements Deck {

    private final Queue<Card> queue;

    public FixedDeck(List<Card> cards) {
        queue = new LinkedList<>(cards);
    }

    @Override
    public Card draw() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.poll();
    }
}
