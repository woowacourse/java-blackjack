package blackjack.domain.card;

import java.util.Objects;

public class Score {
    private static final Score EXTRA_SCORE_USING_ACE = Score.of(10);
    private static final Score BLACK_JACK = Score.of(21);
    private static final Score[] SCORES = new Score[30];

    static {
        for (int i = 0; i < SCORES.length; i++) {
            SCORES[i] = new Score(i);
        }
    }

    private final int value;

    private Score(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("유효하지 않은 점수입니다.");
        }
        this.value = value;
    }

    public static Score of(final int score) {
        if (score < SCORES.length && SCORES[score] != null) {
            return SCORES[score];
        }
        return new Score(score);
    }

    public boolean isBust() {
        return isHigher(BLACK_JACK);
    }

    public boolean isBlackJack() {
        return equals(BLACK_JACK);
    }

    public Score useAceAsEleven() {
        if(addScore(EXTRA_SCORE_USING_ACE).isBust()) {
            return Score.of(value);
        }
        return addScore(EXTRA_SCORE_USING_ACE);
    }

    public Score addScore(final Score score) {
        return Score.of(score.value + this.value);
    }

    public boolean isHigher(final Score score) {
        return this.value > score.value;
    }

    public boolean isLower(final Score score) {
        return this.value < score.value;
    }

    public boolean isBelow(final Score score) {
        return this.value <= score.value;
    }

    @Override
    public boolean equals(final Object o){
        if(o instanceof Score){
            return ((Score) o).value == this.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
