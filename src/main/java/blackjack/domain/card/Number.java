package blackjack.domain.card;

import java.util.ArrayList;
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
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    HIDDEN_ACE(11, "NONE");

    private final int value;
    private final String name;

    Number(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static int sum(List<Number> numbers) {
        numbers = new ArrayList<>(numbers);
        numbers.sort((o1, o2) -> o2.value - o1.value);
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += getNumberOrHiddenAce(numbers, i, sum).value;
        }
        return sum;
    }

    public static Number getNumberOrHiddenAce(List<Number> numbers, int index, int sum) {
        Number number = numbers.get(index);
        if (number == ACE && sum < HIDDEN_ACE.value && isEndIndex(index, numbers)) {
            return HIDDEN_ACE;
        }
        return number;
    }

    private static boolean isEndIndex(int index, List<Number> numbers) {
        return numbers.size() - 1 == index;
    }

    public String getName() {
        return name;
    }
}
