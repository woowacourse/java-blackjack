package bet;

import java.util.LinkedHashMap;
import java.util.Map;
import player.Player;
import result.MatchResult;

public class BetManager {
    private final Map<Player, Bet> wager;

    public BetManager() {
        wager = new LinkedHashMap<>();
    }

    public void addBet(Player player, int amount) {
        wager.put(player, Bet.createInitialBet(amount));
    }

    public void calculateBettingResult(Map<Player, MatchResult> matchResults) {
        wager.forEach((key, value) -> wager.put(key, value.calculateBettingResult(matchResults.get(key))));
    }

    public Bet getBet(Player player) {
        if (!wager.containsKey(player)) {
            throw new IllegalStateException("플레이어가 존재하지 않을 경우의 수가 없습니다.");
        }

        return wager.get(player);
    }

    public int calculateDealerBettingResult() {
        return - wager.values().stream()
                .mapToInt(Bet::getAmount)
                .sum();
    }

    public Map<Player, Bet> getWager() {
        return wager;
    }
}
