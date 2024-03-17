package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingCashier {

    private final Map<Player, Profit> profitMap;
    private final Profit dealerProfit;

    private BettingCashier(Map<Player, Profit> profitMap, Profit dealerProfit) {
        this.profitMap = profitMap;
        this.dealerProfit = dealerProfit;
    }

    public static BettingCashier of(Betting betting, Result result) {
        Map<Player, Profit> profitMap = new HashMap<>();

        betting.getBettingMap().forEach((key, value) ->
                profitMap.put(key, Profit.of(value, result.getPlayerState(key)))
        );

        return new BettingCashier(profitMap, calculateProfitOfDealer(profitMap));
    }

    private static Profit calculateProfitOfDealer(Map<Player, Profit> profitMap) {
        return profitMap.values().stream()
                .map(Profit::toNegative)
                .reduce(Profit::sum)
                .orElseThrow(IllegalStateException::new);
    }

    public Profit findProfitOfDealer() {
        return dealerProfit;
    }

    public Profit findProfitOf(Player player) {
        return profitMap.get(player);
    }
}
