package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>(List.of(Card.values()));
        Collections.shuffle(cards);
    }

    private Card pick() {
        return cards.remove(cards.size() - 1);
    }

    public List<Card> pick(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> pick())
                .toList();
    }
}
