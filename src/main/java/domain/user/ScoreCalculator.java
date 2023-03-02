package domain.user;

import domain.Number;
import java.util.List;

public class ScoreCalculator {

    private static final int BLACKJACK = 21;
    private static final int ZERO = 0;
    private static final int ACE_ONE = 1;

    public int calculate(List<Integer> numbers) {
        int aceCount = countOfAce(numbers);
        int sum = sum(numbers);

        if (sum > BLACKJACK) {
            sum = calculateAceAsOne(aceCount, sum);
        }

        return sum;
    }

    private int calculateAceAsOne(int aceCount, int sum) {
        while(aceCount > ZERO && sum > BLACKJACK) {
            sum -= Number.ACE.getNumber();
            sum += ACE_ONE;
            aceCount--;
        }
        return sum;
    }

    private int countOfAce(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(number -> number == Number.ACE.getNumber())
                .count();
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(number -> number)
                .sum();
    }
}
