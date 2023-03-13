package blackjack.domain.result;

import blackjack.domain.game.Money;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, Money> results;

    private GameResult(Map<Player, Money> results) {
        this.results = results;
    }

    public static GameResult from(Players players, List<Money> betAmounts) {
        Map<Player, Money> results = new LinkedHashMap<>();
        Dealer dealer = players.getDealer();
        List<Challenger> challengers = players.getChallengers();

        for (int i = 0; i < challengers.size(); i++) {
            Challenger challenger = challengers.get(i);
            Result result = dealer.judge(challenger);
            Money revenue = betAmounts.get(i).calculateRevenue(result);
            results.put(challenger, revenue);
        }
        return new GameResult(results);
    }

    public Money getChallengerRevenue(Player player) {
        return results.get(player);
    }

    public Money getDealerRevenue() {
        return results.values().stream()
                .reduce(Money.ZERO, Money::plus)
                .reverse();
    }
}
