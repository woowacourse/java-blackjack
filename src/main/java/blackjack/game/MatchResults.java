package blackjack.game;

import blackjack.player.Hand;
import blackjack.player.Player;
import java.util.HashMap;
import java.util.Map;

public class MatchResults {

    private final Hand dealerHand;
    private final Map<Player, Integer> results;

    public MatchResults(Hand dealerHand) {
        this.dealerHand = dealerHand;
        this.results = new HashMap<>();
    }

    public void addResult(Player player, Money money) {
        double rateOfPrize = MatchResult.calculateRateOfPrize(player.getHand(), dealerHand);
        int prizeMoney = (int) (money.getMoney() * rateOfPrize);
        results.put(player, prizeMoney);
    }

    public int getResultOf(Player player) {
        if (!results.containsKey(player)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 플레이어입니다.");
        }
        return results.get(player);
    }

    public int getDealerResult() {
        int dealerResult = 0;
        for (Map.Entry<Player, Integer> result : results.entrySet()) {
            dealerResult -= result.getValue();
        }
        return dealerResult;
    }

    public Map<Player, Integer> getResults() {
        return results;
    }
}
