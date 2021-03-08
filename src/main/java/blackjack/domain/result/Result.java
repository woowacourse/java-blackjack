package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.*;

public class Result {
    private final List<Player> players;
    private final Dealer dealer;

    public Result(final List<Player> players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Map<ResultEnum, Integer> checkDealerResult() {
        final Map<ResultEnum, Integer> dealerResult = new HashMap<>();
        for (Player player : players) {
            ResultEnum result = dealer.checkResult(player);
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
        }
        return Collections.unmodifiableMap(dealerResult);
    }

    public Map<Player, ResultEnum> checkPlayerResult() {
        final Map<Player, ResultEnum> playerResult = new LinkedHashMap<>();
        for (Player player : players) {
            playerResult.put(player, player.checkResult(dealer));
        }
        return Collections.unmodifiableMap(playerResult);
    }
}
