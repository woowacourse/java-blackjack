package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.result.JudgeResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TotalProfitCalculator {

    private static final int DEALER_PROFIT_INITIAL = 0;
    private static final int DEALER_PROFIT_RATE_FROM_PLAYER = -1;

    private final Map<String, Integer> totalProfitByParticipant = new LinkedHashMap<>();

    public Map<String, Integer> calculateProfitByParticipant(final List<Player> players, final Dealer dealer) {
        totalProfitByParticipant.put(dealer.getName(), DEALER_PROFIT_INITIAL);
        players.forEach(player -> addPlayersProfit(player, dealer));
        return totalProfitByParticipant;
    }

    private void addPlayersProfit(final Player player, final Dealer dealer) {
        final JudgeResult playerResult = JudgeResult.of(player, dealer);
        final int playerProfit = playerResult.profit(player.getBettingMoney());
        totalProfitByParticipant.put(player.getName(), playerProfit);
        totalProfitByParticipant.put(dealer.getName(),
                totalProfitByParticipant.getOrDefault(dealer.getName(), DEALER_PROFIT_INITIAL)
                        + playerProfit * DEALER_PROFIT_RATE_FROM_PLAYER);
    }
}
