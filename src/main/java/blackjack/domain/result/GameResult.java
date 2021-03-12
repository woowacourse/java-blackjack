package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private static final int CHANGE_TO_NEGATIVE = -1;
    private final Map<Player, Result> playersResult;

    public GameResult(final Dealer dealer, final List<Player> players) {
        playersResult = findPlayersResult(dealer, players);
    }

    private Map<Player, Result> findPlayersResult(final Dealer dealer, final List<Player> players) {
        final Map<Player, Result> playersResult = new HashMap<>();
        for (final Player player : players) {
            playersResult.put(player, player.findResult(dealer));
        }
        return playersResult;
    }

    public Map<Player, Integer> calculatePlayersProfit(final Dealer dealer) {
        final Map<Player, Integer> playersProfit = new HashMap<>();
        playersResult.forEach((key, value) -> {
            playersProfit.put(key, (int) (key.getBettingMoney() * value.findProfitRate(dealer, key)));
        });
        return playersProfit;
    }

    public int calculateDealerProfit(final Map<Player, Integer> playersProfit) {
        return CHANGE_TO_NEGATIVE * playersProfit.values().stream()
                .mapToInt(profit -> profit)
                .sum();
    }

    public Map<Player, Result> getPlayersResult() {
        return playersResult;
    }
}
