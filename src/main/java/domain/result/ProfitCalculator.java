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
        if (resultInfo == ResultInfo.WIN) {
            if (player.isBlackjack()) {
                betTable.placeBet(player.getName(), (int) (betAmount * 1.5) - betAmount);
                return;
            }
            betTable.placeBet(player.getName(), betAmount);
            return;
        }
        if (resultInfo == ResultInfo.DEFEAT) {
            betTable.placeBet(player.getName(), -betAmount);
            return;
        }
        betTable.placeBet(player.getName(), 0);
    }

    public int dealerCalculateProfit() {
        return betTable.getAmountByPlayers().stream()
                .mapToInt(value -> -value)
                .sum();
    }
}
