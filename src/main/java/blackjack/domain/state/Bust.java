package blackjack.domain.state;

import blackjack.domain.ScoreCompareResult;

public class Bust implements State{
    private final int score;

    public Bust(int score) {
        this.score = score;
    }

    @Override
    public ScoreCompareResult compare(State otherState) {
        return otherState.compareWithBust(this);
    }

    @Override
    public ScoreCompareResult compareWithStay(Stay playerStay) {
        return ScoreCompareResult.PLAYER_WIN;
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
