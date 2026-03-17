package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Collectors;

public class StandardDeck implements Deck {

    private final Queue<Card> queue;

    public StandardDeck() {
        queue = new LinkedList<>();
        init();
    }

    private void init() {
        List<Card> cards = Arrays.stream(CardPattern.values())
                .flatMap(pattern -> Arrays.stream(CardNumber.values())
                        .map(number -> Card.from(number.getCourt(), pattern.getName())))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        queue.addAll(cards);
    }

    @Override
    public Card draw() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.poll();
    }
}
