package blackjack.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    private final List<Card> values;

    public Cards(final List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public Cards(final Card... cards) {
        this(
                new ArrayList<>(
                        Arrays.stream(cards)
                                .toList()
                )
        );
    }

    public static Cards empty() {
        return new Cards(Collections.emptyList());
    }

    public List<Integer> calculatePossiblePoints() {
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
        List<Card> pickedCards = List.copyOf(values.subList(0, size));
        values.removeAll(pickedCards);
        return pickedCards;
    }

    private void validatePickSize(final int size) {
        if (values.size() < size) {
            throw new IllegalArgumentException("남은 카드가 부족합니다.");
        }
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
