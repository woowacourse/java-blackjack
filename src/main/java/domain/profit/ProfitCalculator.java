package domain.profit;

import domain.bet.BetTable;
import domain.participant.Player;
import domain.result.Result;
import domain.result.ResultInfo;

public class ProfitCalculator {
    private final BetTable betTable;

    public ProfitCalculator(BetTable betTable) {
        this.betTable = betTable;
    }

    public int playerCalculateProfit(Result result, Player player) {
        ResultInfo resultInfo = result.findPlayerResult(player);
        int betAmount = betTable.findBetAmount(player);
        return resultInfo.calculateProfit(betAmount);
    }
}
