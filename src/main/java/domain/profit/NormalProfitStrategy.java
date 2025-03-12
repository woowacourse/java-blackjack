package domain.profit;

import domain.BattleResult;
import domain.Bet;
import java.util.Map;

public class NormalProfitStrategy implements ProfitStrategy {

    private static final Map<BattleResult, Double> WEIGHT_BY_BATTLE_RESULT = Map.ofEntries(
            Map.entry(BattleResult.BLACKJACK, 1.5),
            Map.entry(BattleResult.NORMAL_WIN, 1.0),
            Map.entry(BattleResult.LOSE, -1.0),
            Map.entry(BattleResult.DRAW, 0.0)
    );

    private static final NormalProfitStrategy INSTANCE = new NormalProfitStrategy();

    private NormalProfitStrategy() {
    }

    public static NormalProfitStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Profit calculateProfit(Bet bet, BattleResult battleResult) {
        return new Profit((int) (bet.getValue() * WEIGHT_BY_BATTLE_RESULT.get(battleResult)));
    }
}
