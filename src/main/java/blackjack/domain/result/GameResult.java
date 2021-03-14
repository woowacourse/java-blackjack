package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private static final int CHANGE_TO_NEGATIVE = -1;
    private final Map<Player, Integer> playersProfit;
    private final int dealerProfit;

    public GameResult(final Dealer dealer, final List<Player> players) {
        playersProfit = calculatePlayersProfit(dealer, players);
        dealerProfit = calculateDealerProfit(playersProfit);
    }

    private Map<Player, Integer> calculatePlayersProfit(final Dealer dealer, final List<Player> players) {
        final Map<Player, Integer> playersProfit = new HashMap<>();
        players.forEach(player -> {
            final Result result = player.findResult(dealer);
            playersProfit.put(player, (int) (player.getBettingMoney() * result.findProfitRate(dealer, player)));
        });
        return playersProfit;
    }

    private int calculateDealerProfit(final Map<Player, Integer> playersProfit) {
        return CHANGE_TO_NEGATIVE * playersProfit.values().stream()
                .mapToInt(profit -> profit)
                .sum();
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public Map<Player, Integer> getPlayersProfit() {
        return playersProfit;
    }
}
