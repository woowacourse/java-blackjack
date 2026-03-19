package blackjack.domain.state;

import blackjack.domain.Cards;
import blackjack.domain.ScoreCompareResult;

public class Blackjack implements State{

    @Override
    public ScoreCompareResult compare(State otherState) {
        return otherState.compareWithBlackjack(this);
    }

    @Override
    public ScoreCompareResult compareWithStay(Stay playerStay) {
        return ScoreCompareResult.PLAYER_LOSS;
    }

    @Override
    public ScoreCompareResult compareWithBlackjack(Blackjack playerBlackjack) {
        return ScoreCompareResult.PUSH;
    }

    @Override
    public ScoreCompareResult compareWithBust(Bust playerBust) {
        return ScoreCompareResult.PLAYER_LOSS;
    }

    @Override
    public int getScore() {
        return Cards.BLACKJACK_SCORE;
    }
}
