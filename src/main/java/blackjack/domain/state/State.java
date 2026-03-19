package blackjack.domain.state;

import blackjack.domain.ScoreCompareResult;

public interface State {
    ScoreCompareResult compare(State otherState);

    ScoreCompareResult compareWithStay(Stay playerStay);
    ScoreCompareResult compareWithBlackjack(Blackjack playerBlackjack);
    ScoreCompareResult compareWithBust(Bust playerBust);

    int getScore();
}
