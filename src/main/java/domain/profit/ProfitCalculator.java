package domain.profit;

import domain.bet.BetTable;
import domain.participant.Player;
import domain.result.Result;

public class ProfitCalculator {
    private final BetTable betTable;

    public ProfitCalculator(BetTable betTable) {
        this.betTable = betTable;
    }

    public int playerCalculateProfit(Result result, Player player) {
        int betAmount = betTable.findBetAmount(player);
        return result.calculateProfitFor(player, betAmount);
    }
}
