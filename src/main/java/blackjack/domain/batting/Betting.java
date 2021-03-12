package blackjack.domain.batting;

import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class Betting {

    private final Map<Gamer, Double> gamersBattingAmount;

    public Betting() {
        this.gamersBattingAmount = new LinkedHashMap<>();
    }

    public void betMoney(Gamer gamer, double money) {
        if (gamersBattingAmount.containsKey(gamer)) {
            throw new IllegalArgumentException("[ERROR] 이미 베팅한 게이머입니다.");
        }
        gamersBattingAmount.computeIfAbsent(gamer, (key) -> money);
    }

    public BettingResult calculateGamersProfit(GameResult gameResult) {
        Map<Player, Double> profitResult = new LinkedHashMap<>();
        gameResult.getGamersResult().forEach((gamer, result) -> {
            double bettingAmount = gamersBattingAmount.getOrDefault(gamer, 0.0);
            int profitWeight = result.getProfit();
            profitResult.computeIfAbsent(gamer, (key) -> gamer.profit(bettingAmount * profitWeight));
        });
        double dealerProfit = calculateDealerProfit(profitResult);
        return BettingResult.of(profitResult, dealerProfit);
    }

    private double calculateDealerProfit(Map<Player, Double> gamersProfit) {
        return gamersProfit.values()
            .stream()
            .mapToDouble(gamerProfit -> Double.valueOf(-1 * gamerProfit))
            .sum();
    }
}
