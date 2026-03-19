package blackjack.domain.state;

import blackjack.domain.Cards;
import blackjack.domain.ScoreCompareResult;

public class Blackjack implements State{
    @Override
    public ScoreCompareResult compare(State otherState) {
        if (otherState instanceof Blackjack) {
            return ScoreCompareResult.PUSH;
        }
        return ScoreCompareResult.PLAYER_WIN;
    }

    @Override
    public int getScore() {
        return Cards.BLACKJACK_SCORE;
    }
}
