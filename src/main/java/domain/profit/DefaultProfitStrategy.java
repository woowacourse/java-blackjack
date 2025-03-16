package domain.profit;

public class DefaultProfitStrategy implements ProfitStrategy {

    private static final DefaultProfitStrategy INSTANCE = new DefaultProfitStrategy();

    private DefaultProfitStrategy() {
    }

    public static DefaultProfitStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public Profit calculateProfit(Bet bet, UserBattleResult userBattleResult) {
        return new Profit((int) (bet.getValue() * userBattleResult.getEarningRate()));
    }
}
