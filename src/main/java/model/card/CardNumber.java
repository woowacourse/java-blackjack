package model.card;

import java.util.List;

public enum CardNumber {
    ACE(List.of(1,11)),
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    KING(List.of(10)),
    QUEEN(List.of(10)),
    JACK(List.of(10)),
    ;

    private final List<Integer> numbers;

    public int minimumNumber() {
        return numbers.get(0);
    }

    public int maximumNumber() {
        return numbers.get(numbers.size() - 1);
    }

    CardNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers.stream().sorted().toList();
    }
}
