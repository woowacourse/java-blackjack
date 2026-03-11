package domain.result;

import domain.bet.BetTable;
import domain.participant.Player;

public class ProfitCalculator {
    private final BetTable betTable;

    public ProfitCalculator(BetTable betTable) {
        this.betTable = betTable;
    }

    public void playerCalculateProfit(ResultInfo resultInfo, Player player) {
        int betAmount = betTable.getAmountByName(player.getName());
        int calculatedBetAmount = resultInfo.calculateProfit(betAmount);
        betTable.placeBet(player.getName(), calculatedBetAmount);
    }

    public int dealerCalculateProfit() {
        return betTable.getAmountByPlayers().stream()
                .mapToInt(value -> -value)
                .sum();
    }
}
