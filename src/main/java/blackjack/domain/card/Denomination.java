package blackjack.domain.card;

import java.util.List;

public enum Denomination {

    A(List.of(1, 11)),
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    TEN(List.of(10)),
    K(List.of(10)),
    Q(List.of(10)),
    J(List.of(10));

    private final List<Integer> numbers;

    Denomination(final List<Integer> numbers) {
        this.numbers = numbers;
    }
}
