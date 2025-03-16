package domain.profit;

import java.util.Map;

public class DefaultProfitStrategy implements ProfitStrategy {

    private static final DefaultProfitStrategy INSTANCE = new DefaultProfitStrategy();
    private static final Map<UserBattleResult, Double> EARNING_RATE_BY_BATTLE_RESULT = Map.ofEntries(
            Map.entry(UserBattleResult.BLACKJACK_WIN, 1.5),
            Map.entry(UserBattleResult.NORMAL_WIN, 1.0),
            Map.entry(UserBattleResult.LOSE, -1.0),
            Map.entry(UserBattleResult.DRAW, 0.0)
    );

    private DefaultProfitStrategy() {
    }

    public static DefaultProfitStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Profit calculateProfit(Bet bet, UserBattleResult userBattleResult) {
        return new Profit((int) (bet.getValue() * EARNING_RATE_BY_BATTLE_RESULT.get(userBattleResult)));
    }
}
