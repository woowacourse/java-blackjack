package blackjack.domain.betting;

import java.util.EnumMap;
import java.util.Map;

public class StandardDividendPolicy implements DividendPolicy {

    private static final Map<BettingResult, Double> PROFIT_RATES = new EnumMap<>(BettingResult.class);

    static {
        PROFIT_RATES.put(BettingResult.BLACKJACK_WIN, 1.5);
        PROFIT_RATES.put(BettingResult.WIN, 1.0);
        PROFIT_RATES.put(BettingResult.PUSH, 0.0);
        PROFIT_RATES.put(BettingResult.LOSE, -1.0);
    }

    public int calculate(int betMoney, BettingResult bettingResult) {
        double profitRate = PROFIT_RATES.get(bettingResult);
        return (int) Math.round(betMoney * profitRate);
    }
}
