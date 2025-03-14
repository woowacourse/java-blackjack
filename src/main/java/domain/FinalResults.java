package domain;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FinalResults {

    private final Map<Player, FinalResult> finalResults;

    public FinalResults(final Map<Player, FinalResult> finalResults) {
        this.finalResults = new HashMap<>(finalResults);
    }

    public Map<Gamer, Integer> createProfitResult(Dealer dealer) {
        Map<Gamer, Integer> profitResults = new LinkedHashMap<>();

        int dealerProfit = 0;

        for (Map.Entry<Player, FinalResult> entry : finalResults.entrySet()) {
            Player player = entry.getKey();
            FinalResult result = entry.getValue();

            dealerProfit -= calculatePlayerProfit(player, result);
        }
        profitResults.put(dealer, dealerProfit);

        for (Map.Entry<Player, FinalResult> entry : finalResults.entrySet()) {
            Player player = entry.getKey();
            FinalResult result = entry.getValue();

            int playerProfit = calculatePlayerProfit(player, result);
            profitResults.put(player, playerProfit);
        }

        return profitResults;
    }

    private int calculatePlayerProfit(Player player, FinalResult result) {

        if (result == FinalResult.WIN) {
            return player.winBetting(0);
        }
        if (result == FinalResult.LOSE) {
            return player.loseBetting(0);
        }
        return 0;
    }
}
