package blackjack.domain.batting;

import blackjack.domain.Money;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import blackjack.domain.result.GameResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class Betting {

    private final Map<Gamer, Money> gamersBattingAmount;

    public Betting() {
        this.gamersBattingAmount = new LinkedHashMap<>();
    }

    public void betMoney(Gamer gamer, Money money) {
        if (gamersBattingAmount.containsKey(gamer)) {
            throw new IllegalArgumentException("[ERROR] 이미 베팅한 게이머입니다.");
        }
        gamersBattingAmount.computeIfAbsent(gamer, (key) -> money);
    }

    public BettingResult calculateGamersProfit(GameResult gameResult) {
        Map<Player, Money> profitResult = new LinkedHashMap<>();
        gameResult.getGamersResult().forEach((gamer, result) -> {
            Money bettingAmount = gamersBattingAmount.getOrDefault(gamer, Money.ZERO);
            int profitWeight = result.getProfit();
            profitResult.computeIfAbsent(gamer, (key) -> gamer.profit(bettingAmount.multiply(Money.of(profitWeight))));
        });
        Money dealerProfit = calculateDealerProfit(profitResult);
        return BettingResult.of(profitResult, dealerProfit);
    }

    private Money calculateDealerProfit(Map<Player, Money> gamersProfit) {
        double dealerProfit = gamersProfit.values()
            .stream()
            .mapToDouble(gamerProfit -> Double.valueOf(-1 * gamerProfit.toDouble()))
            .sum();
        return Money.of(dealerProfit);
    }
}
