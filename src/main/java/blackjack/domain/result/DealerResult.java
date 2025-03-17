package blackjack.domain.result;

public class DealerResult {
    private final Score score;

    public DealerResult(Score score) {
        this.score = score;
    }

    public int getScoreValue() {
        return score.getScoreValue();
    }
}
