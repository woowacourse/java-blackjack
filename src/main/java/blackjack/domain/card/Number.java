package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Number {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    J(11, "J"),
    Q(12, "Q"),
    K(13, "K");

    private final int number;
    private final String state;

    Number(final int number, final String state) {
        this.number = number;
        this.state = state;
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

    public String getState() {
        return state;
    }
}
