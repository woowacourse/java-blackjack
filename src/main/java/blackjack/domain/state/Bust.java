package blackjack.domain.state;

import blackjack.domain.ScoreCompareResult;

public class Bust implements State{
    @Override
    public ScoreCompareResult compare(State otherState) {
        return ScoreCompareResult.PLAYER_LOSS;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
