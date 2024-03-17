package blackjack.model.card;

public class Score {

    private final int score;

    public Score(int score) {
        validatePositive(score);
        this.score = score;
    }

    private void validatePositive(int score) {
        if (score < 0) {
            String errorMessage = String.format("[ERROR] 점수는 음수일 수 없습니다.(등록 점수 : %d", score);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public int getScore() {
        return score;
    }
}
