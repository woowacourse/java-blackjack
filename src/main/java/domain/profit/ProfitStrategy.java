package domain.profit;

public interface ProfitStrategy {

    Profit calculateProfit(Bet bet, UserBattleResult userBattleResult);
}
