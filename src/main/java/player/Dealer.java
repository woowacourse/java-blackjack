package player;

public class Dealer extends Player {

    private static final int MAX_DRAWABLE_SCORE = 16;

    public boolean hasDrawableScore() {
        return hand.calculateScore() <= MAX_DRAWABLE_SCORE;
    }

    public boolean determineResult(int score) {
        // TODO: 무승부 처리
        return hand.calculateScore() > score;
    }
}
