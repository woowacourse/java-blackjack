package domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class CardDeque {

    private Deque<Card> deque;

    public CardDeque(Cards cards) {
        this.deque = new ArrayDeque<>(cards.getCards());
    }

    public Card getAndRemoveFrontCard() {
        return deque.removeFirst();
    }

    public Deque<Card> getDeque() {
        return deque;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardDeque cardDeque = (CardDeque) o;
        return Objects.equals(deque, cardDeque.deque);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(deque);
    }
}
