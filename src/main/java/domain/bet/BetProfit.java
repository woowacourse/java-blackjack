package domain.bet;

import static message.ErrorMessage.PLAYER_NOT_IN_BETTING;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BetProfit {
    private static final int ZERO = 0;

    private final Map<Name, Integer> betProfit = new LinkedHashMap<>();

    public BetProfit(List<Name> playerNames) {
        playerNames.forEach(
                playerName -> {
                    betProfit.put(playerName, ZERO);
                }
        );
    }

    public void calculateProfit(Map<Name, GameResult> playerResults, Map<Name, Integer> betHistory) {
        validatePlayers(playerResults.keySet());

        playerResults.keySet().forEach(
                playerName -> {
                    int betAmount = betHistory.get(playerName);
                    GameResult gameResult = playerResults.get(playerName);

                    betAmount = calculateBetAmount(gameResult, betAmount);

                    betProfit.put(playerName, betAmount);
                }
        );
    }

    private void validatePlayers(Set<Name> playerNames) {
        boolean match = playerNames.stream()
                .allMatch(betProfit::containsKey);

        if (!match) {
            throw new IllegalArgumentException(PLAYER_NOT_IN_BETTING.getMessage());
        }
    }

    private int calculateBetAmount(GameResult gameResult, int betAmount) {
        return (int) (betAmount * gameResult.getProfitRatio());
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
