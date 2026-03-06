package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
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
    }

    public Card draw() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        Collections.shuffle((List<?>) queue);
        return queue.poll();
    }

    private void makeCard(String cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            queue.add(new Card(cardNumber.getCourt(), cardPattern));
        }
    }
}
