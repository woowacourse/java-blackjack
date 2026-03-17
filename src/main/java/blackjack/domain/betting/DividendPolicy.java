package blackjack.domain.betting;

import blackjack.domain.GameResult;
import blackjack.domain.participant.BettingAmount;

public interface DividendPolicy {

    long calculate(BettingAmount bettingAmount, GameResult gameResult);
}
