package blackjack.domain.state;

import blackjack.domain.ScoreCompareResult;

public class Stay implements State {
    private final int score;

    public Stay(int score) {
        this.score = score;
    }

    @Override
    public ScoreCompareResult compare(State otherState) {
        return otherState.compareWithStay(this);
    }

    @Override
    public ScoreCompareResult compareWithStay(Stay playerStay) {
        if (playerStay.getScore() > this.score) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        if (playerStay.getScore() < this.score) {
            return ScoreCompareResult.PLAYER_LOSS;
        }
        return ScoreCompareResult.PUSH;
    }

    @Override
    public ScoreCompareResult compareWithBlackjack(Blackjack playerBlackjack) {
        return ScoreCompareResult.PLAYER_WIN;
    }

    @Override
    public ScoreCompareResult compareWithBust(Bust playerBust) {
        return ScoreCompareResult.PLAYER_LOSS;
    }

    @Override
    public int getScore() {
        return score;
    }
}
