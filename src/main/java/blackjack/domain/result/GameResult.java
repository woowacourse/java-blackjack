package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, Integer> results;

    private GameResult(Map<Player, Integer> results) {
        this.results = results;
    }

    public static GameResult from(Players players, List<Integer> betAmounts) {
        Map<Player, Integer> results = new LinkedHashMap<>();
        Dealer dealer = players.getDealer();
        List<Challenger> challengers = players.getChallengers();

        for (int i = 0; i < challengers.size(); i++) {
            Challenger challenger = challengers.get(i);
            Result result = dealer.judge(challenger);
            int revenue = (int) Math.round(betAmounts.get(i) * result.getRateOfReturn());
            results.put(challenger, revenue);
        }
        return new GameResult(results);
    }

    public Integer getChallengerRevenue(Player player) {
        return results.get(player);
    }

    public Integer getDealerRevenue() {
        int sum = 0;
        for (Integer value : results.values()) {
            sum += value;
        }
        return -sum;
    }
}
