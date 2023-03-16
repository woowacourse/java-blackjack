package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.result.JudgeResult;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TotalProfitCalculator {

    private static final BigDecimal DEALER_PROFIT_INITIAL = BigDecimal.ZERO;
    private static final BigDecimal DEALER_PROFIT_RATE_FROM_PLAYER = new BigDecimal("-1");

    private final Map<String, BigDecimal> totalProfitByParticipant = new LinkedHashMap<>();

    public Map<String, BigDecimal> calculateProfitByParticipant(final List<Player> players, final Dealer dealer) {
        totalProfitByParticipant.put(dealer.getName(), DEALER_PROFIT_INITIAL);
        players.forEach(player -> addPlayersProfit(player, dealer));
        return totalProfitByParticipant;
    }

    private void addPlayersProfit(final Player player, final Dealer dealer) {
        final BigDecimal playerProfit = JudgeResult.of(player, dealer).profit(player.getBettingMoney());
        final BigDecimal dealerProfit = playerProfit.multiply(DEALER_PROFIT_RATE_FROM_PLAYER);
        totalProfitByParticipant.put(player.getName(), playerProfit);
        totalProfitByParticipant.put(dealer.getName(),
                totalProfitByParticipant.getOrDefault(dealer.getName(), DEALER_PROFIT_INITIAL).add(dealerProfit));
    }
}
