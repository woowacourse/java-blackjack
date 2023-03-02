package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
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
    J(11),
    Q(12),
    K(13);

    private final int number;

    Number(final int number) {
        this.number = number;
    }

    public static int convertNumberToScore(Number number) {
        if (number == J || number == Q || number == K) {
            return 10;
        }
        return number.number;
    }

    public static Number pickRandomNumber() {
        List<Number> numbers = Arrays.asList(Number.values());
        Collections.shuffle(numbers);

        return numbers.get(0);
    }
}
