package domain;

import java.math.BigDecimal;
import java.util.Map;

public class Result {
    private final Dealer dealer;
    private final Map<Player, MatchResult> playerResults;

    public Result(Dealer dealer, Map<Player, MatchResult> playerResults) {
        this.dealer = dealer;
        this.playerResults = playerResults;
    }

    public int getPlayerProfit(Player player) {
        MatchResult matchResult = playerResults.get(player);
        BigDecimal profit = matchResult.getMultiplier()
                .multiply(BigDecimal.valueOf(player.getBettingMoney().bettingMoney()));
        return profit.intValue();
    }

    public int getDealerProfit() {
        return -playerResults.keySet().stream()
                .mapToInt(this::getPlayerProfit)
                .sum();
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Map<Player, MatchResult> getPlayerResults() {
        return Map.copyOf(playerResults);
    }
}
