package domain;

public class HandStatus {
    private final int score;
    private final int handCount;

    public HandStatus(int score, int handCount) {
        this.score = score;
        this.handCount = handCount;
    }

    public int getScore() {
        return score;
    }

    public int getHandCount() {
        return handCount;
    }
}
