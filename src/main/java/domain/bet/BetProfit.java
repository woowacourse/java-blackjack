package domain.bet;

import static message.ErrorMessage.PLAYER_NOT_IN_BETTING;

import domain.enums.GameResult;
import domain.participant.Name;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BetProfit {
    private final Map<Name, Profit> betProfit;

    private BetProfit(Map<Name, Profit> betProfit) {
        this.betProfit = new LinkedHashMap<>(betProfit);
    }

    public static BetProfit calculateProfit(Map<Name, GameResult> playerResults, Map<Name, Money> betHistory) {
        validatePlayers(playerResults.keySet(), betHistory.keySet());
        Map<Name, Profit> betProfit = new LinkedHashMap<>();

        playerResults.keySet().forEach(
                playerName -> {
                    Money bettingMoney = betHistory.get(playerName);
                    GameResult gameResult = playerResults.get(playerName);

                    Profit profit = gameResult.calculateProfit(bettingMoney);

                    betProfit.put(playerName, profit);
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

    public Map<Name, Profit> getPlayerBetProfit() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(betProfit));
    }

    public Profit getDealerBetProfit() {
        Profit playerProfitSum = new Profit(0);

        for (Profit profit : betProfit.values()) {
            playerProfitSum = playerProfitSum.plus(profit);
        }

        return playerProfitSum.negate();
    }
}
