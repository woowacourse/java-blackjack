package domain;

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
        return (int) matchResult.getMultiplier() * player.getBettingMoney().bettingMoney();
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
