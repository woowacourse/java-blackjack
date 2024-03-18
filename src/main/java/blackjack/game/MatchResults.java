package blackjack.game;

import blackjack.player.Dealer;
import blackjack.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchResults {

    private final Map<Player, Integer> results;

    public MatchResults(Dealer dealer, Map<Player, Money> playerBettingMoney) {
        Map<Player, Integer> results = new LinkedHashMap<>();
        for (Map.Entry<Player, Money> bettingMoney : playerBettingMoney.entrySet()) {
            int prizeMoney = calculatePrizeMoney(dealer, bettingMoney.getKey(), bettingMoney.getValue());
            results.put(bettingMoney.getKey(), prizeMoney);
        }
        this.results = Map.copyOf(results);
    }

    private int calculatePrizeMoney(Dealer dealer, Player player, Money money) {
        double rateOfPrize = MatchResult.calculateRateOfPrize(player.getHand(), dealer.getHand());
        return (int) (money.getMoney() * rateOfPrize);
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
