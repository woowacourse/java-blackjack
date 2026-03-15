package domain.bet;

import static message.ErrorMessage.PLAYER_NOT_IN_BETTING;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BetProfit {
    private final Map<Name, Integer> betProfit;

    private BetProfit(Map<Name, Integer> betProfit) {
        this.betProfit = new LinkedHashMap<>(betProfit);
    }

    public static BetProfit calculateProfit(Map<Name, GameResult> playerResults, Map<Name, Integer> betHistory) {
        validatePlayers(playerResults.keySet(), betHistory.keySet());
        Map<Name, Integer> betProfit = new LinkedHashMap<>();

        playerResults.keySet().forEach(
                playerName -> {
                    int betAmount = betHistory.get(playerName);
                    GameResult gameResult = playerResults.get(playerName);

                    betAmount = gameResult.calculateProfit(betAmount);

                    betProfit.put(playerName, betAmount);
                }
        );

        return new BetProfit(betProfit);
    }

    private static void validatePlayers(Set<Name> playerNames, Set<Name> bettingPlayers) {
        boolean match = bettingPlayers.containsAll(playerNames);

        if (!match) {
            throw new IllegalArgumentException(PLAYER_NOT_IN_BETTING.getMessage());
        }
    }

    public Map<Name, Integer> getPlayerBetProfit() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(betProfit));
    }

    public int getDealerBetProfit() {
        int playerProfitSum = betProfit.values()
                .stream()
                .mapToInt(i -> i)
                .sum();

        return negateBetAmount(playerProfitSum);
    }

    private int negateBetAmount(int betAmount) {
        return -betAmount;
    }
}
