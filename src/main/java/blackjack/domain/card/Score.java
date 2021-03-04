package blackjack.domain.card;

import java.util.Objects;

public class Score {
    public final static int BLACK_JACK = 21;

    private final int score;

    public Score(final int value){
        if(value < 0){
            throw new IllegalArgumentException("유효하지 않은 점수입니다.");
        }

        this.score = value;
    }

    public boolean isBust() {
        return score > BLACK_JACK;
    }

    public boolean isBlackJack(){
        return score == BLACK_JACK;
    }

    public boolean isBelow(final int score) {
        return this.score <= score;
    }

    public boolean isBiggerThan(Score target){
        return score > target.score;
    }

    public boolean isLessThan(Score target){
        return score < target.score;
    }

    public int score(){
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return this.score == score.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
