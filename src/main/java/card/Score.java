package card;

public class Score {
    public static final int MAX_SCORE = 21;
    public static final int LIMIT_ADDITIONAL_SCORE = 11;
    public static final int ADDITIONAL_SCORE = 10;
    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isMaxScore() {
        return score == MAX_SCORE;
    }

    public boolean canAddAdditionalScore() {
        return score <= LIMIT_ADDITIONAL_SCORE;
    }

    public Score addAdditionalScore() {
        if(canAddAdditionalScore()) {
            return new Score(score + ADDITIONAL_SCORE);
        }
        return this;
    }

    public int getScore() {
        return score;
    }
}
