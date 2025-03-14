package domain.game;

public class ScoreInfo {

    public static final int BLACK_JACK_SCORE = 21;
    public static final int BLACK_JACK_SIZE = 2;

    private final int score;
    private final int cardSize;

    public ScoreInfo(int score, int cardSize) {
        this.score = score;
        this.cardSize = cardSize;
    }

    public boolean isBlackJack() {
        return (score == BLACK_JACK_SCORE && cardSize == BLACK_JACK_SIZE);
    }

    public boolean isBust() {
        return (score > BLACK_JACK_SCORE);
    }
}
