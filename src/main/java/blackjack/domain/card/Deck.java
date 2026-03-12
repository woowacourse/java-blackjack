package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(Collection<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public static Deck create(CardsGenerator cardsGenerator) {
        return new Deck(cardsGenerator.create());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있습니다.");
        }
        return cards.poll();
    }
}
