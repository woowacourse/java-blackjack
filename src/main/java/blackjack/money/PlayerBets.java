package blackjack.money;

import blackjack.game.MatchResults;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerBets {

    private final Map<String, PlayerBet> playerBets;

    public PlayerBets() {
        this.playerBets = new LinkedHashMap<>();
    }

    public void addPlayerBet(String name, BetMoney betMoney) {
        playerBets.put(name, new PlayerBet(name, betMoney));
    }

    public int calculateDealerProfit(MatchResults matchResults) {
        int playersProfit = playerBets.values()
                .stream()
                .mapToInt(matchResults::calculateProfitByBet)
                .sum();
        return playersProfit * -1;
    }

    public List<PlayerBet> getPlayerBets() {
        return List.copyOf(playerBets.values());
    }
}
