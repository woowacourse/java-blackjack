package domain.user;

import domain.Card.Card;
import domain.Number;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {

    private static final int BLACKJACK = 21;
    private static final int ZERO = 0;
    private static final int ACE_ONE = 1;

    public int calculate(List<Card> cards) {
        List<Integer> numbers = convertCardsToNumbers(cards);
        int aceCount = countOfAce(numbers);
        int sum = sum(numbers);

        if (sum > BLACKJACK) {
            sum = calculateAceAsOne(aceCount, sum);
        }
        return sum;
    }

    private List<Integer> convertCardsToNumbers(List<Card> cards) {
        return cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toUnmodifiableList());
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

    private int calculateAceAsOne(int aceCount, int sum) {
        while(aceCount > ZERO && sum > BLACKJACK) {
            sum -= Number.ACE.getNumber();
            sum += ACE_ONE;
            aceCount--;
        }
        return sum;
    }
}
