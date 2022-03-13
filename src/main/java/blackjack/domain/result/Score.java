package blackjack.domain.result;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Objects;

public class Score implements Comparable<Score> {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int ACE_NUMBER = 1;
    public static final int ALTERNATE_ACE_VALUE = 10;

    private final int value;

    private Score(int value) {
        this.value = value;
        validateNegative(value);
    }

    public static Score from(List<Card> cards) {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        return new Score(sum);
    }
    private void validateNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
    }

    public Score calculateWithAce() {
        if (value + ALTERNATE_ACE_VALUE > BLACKJACK_NUMBER) {
            return new Score(value);
        }
        return new Score(value + ALTERNATE_ACE_VALUE);
    }

    public boolean isOver(int value) {
        return this.value > value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Score o) {
        return getValue() - o.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                '}';
    }
}
