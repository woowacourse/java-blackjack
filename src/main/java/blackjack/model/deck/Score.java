package blackjack.model.deck;

public class Score {

    private final int score;

    public Score(int score) {
        validateNotNegative(score);
        this.score = score;
    }

    private void validateNotNegative(int score) {
        if (score < 0) {
            String errorMessage = String.format("[ERROR] 점수는 음수일 수 없습니다.(등록 score : %d", score);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public int getScore() {
        return score;
    }
}
