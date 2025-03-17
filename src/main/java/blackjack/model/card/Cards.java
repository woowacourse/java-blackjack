package blackjack.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Cards {

    private static final int BLACK_JACK_SIZE = 2;
    private static final int BLACK_JACK_POINT = 21;
    private static final int BUST_THRESHOLD = 22;

    private final List<Card> values;

    public Cards(final List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public Cards(final Card... cards) {
        this(Arrays.stream(cards)
                .toList()
        );
    }

    private Cards() {
        this.values = new ArrayList<>();
    }

    public static Cards empty() {
        return new Cards();
    }

    public int calculateOptimalPoint() {
        List<Integer> possiblePoints = calculatePossiblePoints();
        return possiblePoints.stream()
                .filter(point -> point < BUST_THRESHOLD)
                .max(Integer::compareTo)
                .orElseGet(() -> possiblePoints.stream()
                        .min(Integer::compareTo)
                        .orElseThrow(IllegalStateException::new)
                );
    }

    private List<Integer> calculatePossiblePoints() {
        List<Integer> possiblePoints = new ArrayList<>();
        addPossiblePoint(possiblePoints, 0, 0);

        return possiblePoints;
    }

    private void addPossiblePoint(List<Integer> possiblePoints, int index, int sum) {
        if (index == values.size()) {
            possiblePoints.add(sum);
            return;
        }
        for (int point : values.get(index).getPoints()) {
            addPossiblePoint(possiblePoints, index + 1, sum + point);
        }
    }

    public boolean hasSize(final int size) {
        return values.size() == size;
    }

    public void merge(final Cards otherCards) {
        this.values.addAll(otherCards.values);
    }

    public List<Card> pick(final int size) {
        validatePickSize(size);
        List<Card> cards = new ArrayList<>(size);
        IntStream.range(0, size)
                .forEach(index -> {
                            cards.add(values.getLast());
                            values.removeLast();
                        }
                );

        return cards;
    }

    private void validatePickSize(final int size) {
        if (values.size() < size) {
            throw new IllegalArgumentException("남은 카드가 부족합니다.");
        }
    }

    public boolean isBlackjack() {
        return calculateOptimalPoint() == BLACK_JACK_POINT && values.size() == BLACK_JACK_SIZE;
    }

    public boolean isBust() {
        return calculateOptimalPoint() >= BUST_THRESHOLD;
    }

    public Card getFirst() {
        return values.getFirst();
    }

    public List<Card> getValues() {
        return List.copyOf(values);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cards cards)) {
            return false;
        }
        return Objects.equals(getValues(), cards.getValues());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValues());
    }

}
