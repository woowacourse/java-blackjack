package blackjack.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Cards {

    private final Queue<Card> values;

    public Cards(final List<Card> values) {
        this.values = new LinkedList<>(values);
    }

    public int sumAll() {
        return values.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public boolean hasSize(final int size) {
        return values.size() == size;
    }

    public void merge(final Cards otherCards) {
        this.values.addAll(otherCards.values);
    }

    public List<Card> getValues() {
        return List.copyOf(values);
    }

    public List<Card> pick(final int size) {
        validatePickSize(size);
        List<Card> cards = new ArrayList<>(size);
        IntStream.range(0, size)
                .forEach(i -> cards.add(values.poll()));

        return cards;
    }

    private void validatePickSize(final int size) {
        if (values.size() < size) {
            throw new IllegalArgumentException("남은 카드가 부족합니다.");
        }
    }
}
