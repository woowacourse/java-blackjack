package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public abstract class Deck {
    protected Deque<Card> cards;

    protected Deck() {
        final List<Card> newCards = CardsCache.getAllCards();
        Collections.shuffle(newCards);
        this.cards = new ArrayDeque<>(newCards);
    }

    public abstract Card draw();
}
