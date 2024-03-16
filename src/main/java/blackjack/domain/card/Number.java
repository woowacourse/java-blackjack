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
        if (!numbers.contains(ACE)) {
            throw new IllegalArgumentException("에이스가 존재하는 경우만 가능한 계산법입니다.");
        }
        return sum(numbers) + VALUE_FOR_SOFT_HAND;
    }

    public int getValue() {
        return value;
    }
}
