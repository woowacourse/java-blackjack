package domain.card;

import java.util.Objects;

public class Score {
    private static final Score MAX = new Score(21);
    private static final Score ACE_BOTH_SCORE_DIFFERENCE = new Score(10);
    private static final int MIN_SCORE = 0;
    
    private final int score;
    
    public Score(int score) {
        validateNegativeScore(score);
        this.score = score;
    }
    
    private void validateNegativeScore(int score) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("Score에 음수는 들어올 수 없습니다.");
        }
    }
    
    public Score plusTenIfNotBust() {
        if (addScore(ACE_BOTH_SCORE_DIFFERENCE).isLessThenOrEqualToMaxScore()) {
            return addScore(ACE_BOTH_SCORE_DIFFERENCE);
        }
        
        return this;
    }
    
    public Score addScore(Score otherScore) {
        return new Score(score + otherScore.score);
    }
    
    private boolean isLessThenOrEqualToMaxScore() {
        return isLessThen(Score.MAX) || equals(Score.MAX);
    }
    
    public boolean isLessThen(Score otherScore) {
        return this.score < otherScore.score;
    }
    
    public boolean isBust() {
        return isOverThen(MAX);
    }
    
    public boolean isOverThen(Score otherScore) {
        return this.score > otherScore.score;
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
}
