import java.util.List;

public enum CardNumber {
    A(List.of(11, 1)),
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    TEN(List.of(10)),
    KING(List.of(10)),
    QUEEN(List.of(10)),
    JACK(List.of(10));

    private final List<Integer> numbers;

    CardNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
