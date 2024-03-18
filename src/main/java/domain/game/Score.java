package domain.game;


import domain.card.Hand;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Score implements Comparable<Score> {

    private static final int BLACK_JACK_SCORE = 21;
    private static final int MINIMUM_SCORE = 2;
    private static final int MAXIMUM_SCORE = 30;
    private static final int ACE_CARD_GAP = 10;
    private static final Map<Integer, Score> SCORE_POOL = new HashMap<>();

    static {
        IntStream.rangeClosed(MINIMUM_SCORE, MAXIMUM_SCORE)
                .forEach(score -> SCORE_POOL.put(score, new Score(score)));
    }

    static final String INVALID_SCORE_MESSAGE = "존재할 수 없는 점수입니다.";

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score from(final Hand hand) {
        int aceCount = hand.countAceCard();
        int sum = hand.calculateSum();

        while (isBustWithAce(sum, aceCount)) {
            aceCount--;
            sum -= ACE_CARD_GAP;
        }
        validateRange(sum);
        return SCORE_POOL.get(sum);
    }

    public static Score from(final int value) {
        validateRange(value);
        return SCORE_POOL.get(value);
    }

    private static void validateRange(final int value) {
        if (value < MINIMUM_SCORE || MAXIMUM_SCORE < value) {
            throw new IllegalArgumentException(INVALID_SCORE_MESSAGE);
        }
    }

    private static boolean isBustWithAce(final int sum, final int aceCount) {
        return sum > BLACK_JACK_SCORE && aceCount > 0;
    }

    public boolean isBust() {
        return value > BLACK_JACK_SCORE;
    }

    public boolean isBlackJack() {
        return value == BLACK_JACK_SCORE;
    }

    public int get() {
        return value;
    }

    @Override
    public int compareTo(final Score that) {
        return Integer.compare(this.value, that.value);
    }

    @Override
    public boolean equals(final Object o) {
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

}
