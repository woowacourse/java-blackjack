package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitCalculator {

    private static final String INVALID_DEALER_PROFIT_ERROR_MESSAGE = "합을 계산할 수 없습니다.";
    private static final int DEALER_EARNING_RATE = -1;

    private final Map<User, Money> profitResult;

    public ProfitCalculator() {
        this.profitResult = new LinkedHashMap<>();
    }

    public Map<User, Money> calculateProfit(Dealer dealer, Map<Player, Status> result) {
        profitResult.put(dealer, calculateDealerProfit(result));
        result.forEach((player, status) -> {
            profitResult
                .put(player, calculatePlayerProfit(player.getMoney(), status.getEarningRate()));
        });
        return profitResult;
    }

    private Money calculateDealerProfit(Map<Player, Status> result) {
        return result.entrySet()
            .stream()
            .map(entry -> calculatePlayerProfit(entry.getKey().getMoney(),
                entry.getValue().getEarningRate() * DEALER_EARNING_RATE))
            .reduce(Money::sum)
            .orElseThrow(() -> new IllegalArgumentException(INVALID_DEALER_PROFIT_ERROR_MESSAGE))
            ;
    }

    private Money calculatePlayerProfit(Money money, double earningRate) {
        return money.multiply(earningRate);
    }
}
