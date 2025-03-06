package blackjack.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.IntStream;

public class Cards {

    private final Queue<Card> values;

    public Cards(final List<Card> values) {
        this.values = new LinkedList<>(values);
    }

    private Cards() {
        this.values = new LinkedList<>();
    }

    public static Cards empty() {
        return new Cards();
    }

    public List<Integer> sumAll() {
        List<Integer> result = new ArrayList<>();
        dfs(result, 0, 0);

        return result;
    }

    public void dfs(List<Integer> result, int index, int sum) {
        if (index == values.size()) {
            result.add(sum);
            return;
        }
        List<Card> listValues = new ArrayList<>(values);
        for (int number : listValues.get(index).getCardNumbers()) {
            dfs(result, index + 1, sum + number);
        }
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
