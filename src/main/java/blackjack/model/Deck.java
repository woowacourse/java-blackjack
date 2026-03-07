package blackjack.model;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(Collection<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public static Deck shuffled(CardsGenerator cardsGenerator) {
        List<Card> cards = cardsGenerator.create();
        Collections.shuffle(cards);

        return new Deck(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있습니다.");
        }

        return cards.poll();
    }
}
