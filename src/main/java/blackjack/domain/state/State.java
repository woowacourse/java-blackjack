package blackjack.domain.state;

import blackjack.domain.ScoreCompareResult;

public interface State {
    ScoreCompareResult compare(State otherState);
    int getScore();
}
