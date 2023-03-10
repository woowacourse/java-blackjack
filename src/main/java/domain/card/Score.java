package domain.card;

public class Score {
    private static final Score MAX = new Score(21);
    private static final Score ACE_BOTH_SCORE_DIFFERENCE = new Score(10);
    
    private final int score;
    
    public Score(int score) {
        this.score = score;
    }
    
    public Score plusTenIfNotBust() {
        if (add(ACE_BOTH_SCORE_DIFFERENCE).isLessThenOrEqualTo(MAX)) {
            return add(ACE_BOTH_SCORE_DIFFERENCE);
        }
        
        return this;
    }
    
    public Score add(Score otherScore) {
        return new Score(score + otherScore.score);
    }
    
    public boolean isLessThenOrEqualTo(Score otherScore) {
        return isLessThen(otherScore) || isSameTo(otherScore);
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
    
    private boolean isSameTo(Score otherScore) {
        return this.score == otherScore.score;
    }
    
    public int getScore() {
        return score;
    }
}
