package blackjack.game;

import blackjack.money.PlayerBet;
import blackjack.player.Player;
import java.util.HashMap;
import java.util.Map;

public class MatchResults {

    private final Map<String, MatchResult> results;

    public MatchResults() {
        this.results = new HashMap<>();
    }

    public void addResult(String playerName, Player player, Player dealer) {
        MatchResult result = player.matchResultVersus(dealer);
        results.put(playerName, result);
    }

    public int calculateProfitByBet(PlayerBet playerBet) {
        String playerName = playerBet.name();
        int betAmount = playerBet.betMoney().getAmount();

        MatchResult result = getResultByName(playerName);
        return result.calculateProfit(betAmount);
    }

    private MatchResult getResultByName(String playerName) {
        if (!results.containsKey(playerName)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 이름입니다.");
        }
        return results.get(playerName);
    }
}
