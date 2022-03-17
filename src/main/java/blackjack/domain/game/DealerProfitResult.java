package blackjack.domain.game;

import blackjack.domain.money.BetAndProfit;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Map;

public class DealerProfitResult {
    private final Dealer dealer;
    private final Map<Player, BetAndProfit> playerBetAndProfits;

    private DealerProfitResult(Dealer dealer, Map<Player, BetAndProfit> playerBetAndProfits) {
        this.dealer = dealer;
        this.playerBetAndProfits = playerBetAndProfits;
    }

    public static DealerProfitResult of(Dealer dealer, Map<Player, BetAndProfit> playerBetAndProfits) {
        return new DealerProfitResult(dealer, playerBetAndProfits);
    }

    public Money getProfit() {
        Money profit = Money.from(0);
        for (Player player : playerBetAndProfits.keySet()) {
            profit = calculateSingleMatchProfit(profit, player);
        }

        return profit;
    }

    // TODO: 파라미터가 변하는 것은 좋지 않은 것 같음
    private Money calculateSingleMatchProfit(Money profit, Player player) {
        Money playerBetMoney = playerBetAndProfits.get(player).getBetMoney();

        if (dealer.compareWith(player) == ResultType.WIN) {
            return profit.add(playerBetMoney);
        }
        if (dealer.compareWith(player) == ResultType.LOSE) {
            return calculateProfitIfLose(profit, player, playerBetMoney);
        }

        return profit;
    }

    private Money calculateProfitIfLose(Money profit, Player player, Money playerBetMoney) {
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return profit.subtract(Money.createBlackjackProfit(playerBetMoney));
        }

        return profit.subtract(playerBetMoney);
    }
}
