package blackjack.domain.betting;

import blackjack.domain.GameResult;
import java.util.EnumMap;
import java.util.Map;

public class StandardDividendPolicy implements DividendPolicy {

    private static final Map<GameResult, Double> PROFIT_RATES = new EnumMap<>(GameResult.class);

    static {
        PROFIT_RATES.put(GameResult.BLACKJACK_WIN, 1.5);
        PROFIT_RATES.put(GameResult.WIN, 1.0);
        PROFIT_RATES.put(GameResult.PUSH, 0.0);
        PROFIT_RATES.put(GameResult.LOSE, -1.0);
    }

    public int calculate(int betMoney, GameResult gameResult) {
        double profitRate = PROFIT_RATES.get(gameResult);
        return (int) Math.round(betMoney * profitRate);
    }
}
