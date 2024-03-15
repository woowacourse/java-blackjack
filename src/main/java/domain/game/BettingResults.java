package domain.game;

import domain.participant.PlayerName;

import java.util.HashMap;
import java.util.Map;

public record BettingResults(int dealerRevenue, Map<PlayerName, Integer> playerRevenues) {

    public static BettingResults of(
            final BettingMoneyStore bettingMoneyStore,
            final BlackjackGameResults blackjackGameResults
    ) {
        int totalDealerRevenue = countTotalDealerRevenue(bettingMoneyStore, blackjackGameResults);
        Map<PlayerName, Integer> playerRevenues = getPlayerRevenues(bettingMoneyStore, blackjackGameResults);

        return new BettingResults(totalDealerRevenue, playerRevenues);
    }

    private static Map<PlayerName, Integer> getPlayerRevenues(
            final BettingMoneyStore bettingMoneyStore,
            final BlackjackGameResults blackjackGameResults
    ) {
        Map<PlayerName, Integer> playerRevenues = new HashMap<>();
        blackjackGameResults.playerGameResults()
                .forEach((key, value) -> {
                    int playerRevenue = bettingMoneyStore.getPlayerRevenue(key, value);
                    playerRevenues.put(key, playerRevenue);
                });

        return playerRevenues;
    }

    private static int countTotalDealerRevenue(
            final BettingMoneyStore bettingMoneyStore,
            final BlackjackGameResults blackjackGameResults
    ) {
        return -(blackjackGameResults.playerGameResults()
                .entrySet()
                .stream()
                .mapToInt(entry -> bettingMoneyStore.getPlayerRevenue(entry.getKey(), entry.getValue()))
                .sum());
    }
}
