package model.card;

import java.util.List;

public enum CardNumber {
    ACE(List.of(1, 11), "A"),
    TWO(List.of(2), "2"),
    THREE(List.of(3), "3"),
    FOUR(List.of(4), "4"),
    FIVE(List.of(5), "5"),
    SIX(List.of(6), "6"),
    SEVEN(List.of(7), "7"),
    EIGHT(List.of(8), "8"),
    NINE(List.of(9), "9"),
    TEN(List.of(10), "10"),
    KING(List.of(10), "K"),
    QUEEN(List.of(10), "Q"),
    JACK(List.of(10), "J"),
    ;

    private final List<Integer> numbers;

    private final String name;

    CardNumber(List<Integer> numbers, String name) {
        this.numbers = numbers;
        this.name = name;
    }

    public int minimumNumber() {
        return numbers.stream()
                .mapToInt(number -> number)
                .min()
                .orElseThrow(() -> new IllegalStateException("숫자가 존재하지 않습니다."));
    }

    public int maximumNumber() {
        return numbers.stream()
                .mapToInt(number -> number)
                .max()
                .orElseThrow(() -> new IllegalStateException("숫자가 존재하지 않습니다."));
    }

    public String getName() {
        return name;
    }
}
