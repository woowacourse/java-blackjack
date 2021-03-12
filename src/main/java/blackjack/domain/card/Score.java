package blackjack.domain.card;

import java.util.Objects;

public class Score {
    private static final int EXTRA_SCORE_USING_ACE = 10;
    private static final int BLACK_JACK = 21;

    private static final Score[] scores = new Score[30];

    static{
        for(int i =0; i<scores.length; i++){
            scores[i] = new Score(i);
        }
    }

    private final int value;

    private Score(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("유효하지 않은 점수입니다.");
        }

        this.value = value;
    }

    public static Score of(final int score){
        if(score < scores.length && scores[score] != null){
            return scores[score];
        }

        return new Score(score);
    }

    public boolean isBust() {
        return value > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return value == BLACK_JACK;
    }

    public boolean isHigher(final Score score){
        return this.value > score.value;
    }

    public boolean isLower(final Score score){
        return this.value < score.value;
    }

    public boolean isBelow(final int score) {
        return value <= score;
    }

    public Score useAceAsEleven() {
        if(value + EXTRA_SCORE_USING_ACE > BLACK_JACK){
            return Score.of(value);
        }
        return Score.of(value + EXTRA_SCORE_USING_ACE);
    }

    public Score addScore(final Score score) {
        return Score.of(score.value + this.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
