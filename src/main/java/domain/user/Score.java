package domain.user;

import domain.card.Card;
import domain.Number;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Score {
    private static final int BLACKJACK = 21;
    private static final int ZERO = 0;
    private static final int ACE_ONE = 1;

    private int score;

    public Score(List<Card> cards) {
        calculate(cards);
    }

    public Score(int score) {
        this.score = score;
    }

    public void calculate(List<Card> cards) {
        List<Integer> numbers = convertCardsToNumbers(cards);
        int aceCount = countOfAce(numbers);
        int sum = sum(numbers);

        if (sum > BLACKJACK) {
            sum = calculateAceAsOne(aceCount, sum);
        }
        score = sum;
    }

    public Score add(Score other) {
        return new Score(this.score + other.getScore());
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    private List<Integer> convertCardsToNumbers(List<Card> cards) {
        return cards.stream()
                .map(Card::getScore)
                .collect(Collectors.toUnmodifiableList());
    }

    private int countOfAce(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(number -> number == Number.ACE.getScore())
                .count();
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(number -> number)
                .sum();
    }

    private int calculateAceAsOne(int aceCount, int sum) {
        while (aceCount > ZERO && sum > BLACKJACK) {
            sum -= Number.ACE.getScore();
            sum += ACE_ONE;
            aceCount--;
        }
        return sum;
    }
}
