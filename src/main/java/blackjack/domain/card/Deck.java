package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {
    private final List<Card> cards;

    public Deck(ShuffleStrategy shuffleStrategy) {
        cards = new ArrayList<>(shuffleStrategy.shuffle(Card.getAll()));
    }

    private Card draw() {
        return cards.remove(cards.size() - 1);
    }

    public List<Card> draw(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> draw())
                .toList();
    }
}
