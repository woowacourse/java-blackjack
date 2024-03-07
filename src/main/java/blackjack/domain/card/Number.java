package blackjack.domain.card;

import java.util.List;

public enum Number {

    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private static final int VALUE_FOR_SOFT_HAND = 10;

    private final int value;

    Number(final int value) {
        this.value = value;
    }

    public static int sum(final List<Number> numbers) {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }

    public static int sumOneAceToSoftHand(final List<Number> numbers) {
        if (numbers.contains(ACE)) {
            return sum(numbers) + VALUE_FOR_SOFT_HAND;
        }
        throw new IllegalArgumentException("에이스가 없다면 소프트핸드 계산법을 사용할 수 없습니다.");
    }

    public int getValue() {
        return value;
    }
}
