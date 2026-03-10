package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class StandardDeck implements Deck {

    private final Queue<Card> queue;

    public StandardDeck() {
        queue = new LinkedList<>();
        init();
    }

    private void init() {
        for (CardPattern cardPattern : CardPattern.values()) {
            makeCard(cardPattern.getName());
        }
        Collections.shuffle((List<?>) queue);
    }

    private void makeCard(String cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            queue.add(new Card(cardNumber.getCourt(), cardPattern));
        }
    }

    @Override
    public Card draw() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.poll();
    }
}
