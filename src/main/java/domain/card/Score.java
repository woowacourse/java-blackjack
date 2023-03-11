package domain.card;

public class Score {
    
    public static final int BOUNDARY_NUMBER = 21;
    private final int score;
    
    public Score(int score) {
        this.score = score;
    }
    
    public static Score base() {
        return new Score(0);
    }
    
    public static Score add(Score score1, Score score2) {
        return new Score(score1.score + score2.score);
    }
    
    public Score addTenIfInBoundary() {
        if (this.score + 10 <= BOUNDARY_NUMBER) {
            return new Score(this.score + 10);
        }
        return this;
    }
    
    public int value() {
        return this.score;
    }
}
