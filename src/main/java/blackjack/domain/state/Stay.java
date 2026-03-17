package blackjack.domain.state;

import blackjack.domain.ScoreCompareResult;

public class Stay implements State {
    private final int score;

    public Stay(int score) {
        this.score = score;
    }

    @Override
    public ScoreCompareResult compare(State otherState) {
        if (otherState instanceof Blackjack) return ScoreCompareResult.PLAYER_LOSS;
        if (otherState instanceof Bust) return ScoreCompareResult.PLAYER_WIN;
        int otherScore = otherState.getScore();
        if (this.score > otherScore) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        if (this.score < otherScore) {
            return ScoreCompareResult.PLAYER_LOSS;
        }
        return ScoreCompareResult.PUSH;
    }

    @Override
    public int getScore() {
        return score;
    }
}
