package domain.profit;

import domain.BattleResult;
import domain.Bet;

public interface ProfitStrategy {
    
    Profit calculateProfit(Bet bet, BattleResult battleResult);
}
