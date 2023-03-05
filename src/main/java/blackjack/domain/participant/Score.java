package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Score {
    private final int value;

    public static Score from(List<Card> cards) {
        Score score = new Score(sumOfNumbers(cards));
        if (containsAce(cards) && score.value <= 11) {
            return score.plus(10);
        }
        return score;
    }

    private static int sumOfNumbers(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private static boolean containsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }


    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Score plus(int increment) {
        return new Score(value + increment);
    }
}
