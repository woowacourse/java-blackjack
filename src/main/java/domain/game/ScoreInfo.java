package domain.game;

public class ScoreInfo {

    public static final int BLACKJACK_SCORE = 21;
    public static final int BLACKJACK_CARD_SIZE = 2;

    private final int score;
    private final int cardSize;

    public ScoreInfo(int score, int cardSize) {
        this.score = score;
        this.cardSize = cardSize;
    }

    public boolean isBlackJack() {
        return (score == BLACKJACK_SCORE && cardSize == BLACKJACK_CARD_SIZE);
    }

    public boolean isBust() {
        return (score > BLACKJACK_SCORE);
    }

    public int getScore() {
        return score;
    }
}
