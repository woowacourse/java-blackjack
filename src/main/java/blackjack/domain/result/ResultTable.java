package blackjack.domain.result;

import blackjack.domain.bet.BettingTable;
import blackjack.domain.bet.Money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultTable {

    private final Map<String, GameResult> table;

    public ResultTable() {
        table = new HashMap<>();
    }

    public void put(final String name, final GameResult gameResult) {
        table.put(name, gameResult);
    }

    public GameResult get(final String name) {
        return table.get(name);
    }

    public List<String> getGameEndedPlayerNames() {
        return List.copyOf(table.keySet());
    }

    public static class ProfitCalculator {

        private final BettingTable bettingTable;
        private final ResultTable resultTable;

        public ProfitCalculator() {
            this.bettingTable = new BettingTable();
            this.resultTable = new ResultTable();
        }

        public void bet(final String name, final int betAmount) {
            bettingTable.bet(name, new Money(betAmount));
        }

        public void putGameResult(final String name, final GameResult result) {
            resultTable.put(name, result);
        }

        public int calculateProfit(final String name) {
            final GameResult gameResult = resultTable.get(name);
            return gameResult.calculateProfit(
                    bettingTable.get(name).getAmount()
            );
        }

        public int calculateDealerProfit() {
            final List<String> playerNames = resultTable.getGameEndedPlayerNames();
            final int playerProfitSum = playerNames.stream()
                    .mapToInt(this::calculateProfit)
                    .sum();
            return -1 * playerProfitSum;
        }
    }
}
