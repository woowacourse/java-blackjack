package domain.card;

import java.util.Objects;

public class Score {
    private static final Score ACE_BOTH_SCORE_DIFFERENCE = new Score(10);
    
    private final int score;
    
    public Score(int score) {
        this.score = score;
    }
    
    public Score plusTenIfLessThenOrEqualTo(Score score) {
        if (addScore(ACE_BOTH_SCORE_DIFFERENCE).isLessThenOrEqualTo(score)) {
            return addScore(ACE_BOTH_SCORE_DIFFERENCE);
        }
        
        return this;
    }
    
    public Score addScore(Score otherScore) {
        return new Score(score + otherScore.score);
    }
    
    private boolean isLessThenOrEqualTo(Score score) {
        return isLessThen(score) || equals(score);
    }
    
    public boolean isLessThen(Score otherScore) {
        return this.score < otherScore.score;
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
