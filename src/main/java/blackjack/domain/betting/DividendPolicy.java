package blackjack.domain.betting;

import blackjack.domain.GameResult;

public interface DividendPolicy {

    int calculate(int betMoney, GameResult gameResult);
}
