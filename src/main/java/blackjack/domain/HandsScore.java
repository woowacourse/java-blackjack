package blackjack.domain;

public class HandsScore {
    private static final int BLACK_JACK = 21;

    private int handsScore;

    public HandsScore() {
        this.handsScore = 0;
    }

    public void addScore(int cardScore) {
        handsScore += cardScore;
    }

    public int getScore() {
        return handsScore;
    }

    public boolean isBurst() {
        return handsScore > BLACK_JACK;
    }
}
