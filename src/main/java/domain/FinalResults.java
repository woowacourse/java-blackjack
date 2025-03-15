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

    public Map<Gamer, Integer> createProfitResult(final Dealer dealer) {
        final Map<Gamer, Integer> profitResults = new LinkedHashMap<>();
        int dealerProfit = 0;

        for (final Map.Entry<Player, FinalResult> entry : finalResults.entrySet()) {
            final Player player = entry.getKey();
            final FinalResult result = entry.getValue();

            final int playerProfit = calculatePlayerProfit(player, result);
            profitResults.put(player, playerProfit);
            dealerProfit -= playerProfit;
        }

        profitResults.put(dealer, dealerProfit);

        return profitResults;
    }

    private int calculatePlayerProfit(final Player player, final FinalResult result) {
        if (result == FinalResult.WIN) {
            return player.winBetting(0);
        }
        if (result == FinalResult.LOSE) {
            return player.loseBetting(0);
        }
        return 0;
    }
}
