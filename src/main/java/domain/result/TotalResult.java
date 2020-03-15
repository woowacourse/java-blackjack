package domain.result;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TotalResult {

    private final Map<Player, WinningResult> playersResult = new LinkedHashMap<>();
    private final Map<WinningResult, Integer> dealerResult = new LinkedHashMap<>();

    private TotalResult(Dealer dealer, Players players) {
        Arrays.stream(WinningResult.values())
                .forEach(winningResult ->
                        dealerResult.put(winningResult, 0));

        players.getPlayers()
                .forEach(player -> playersResult.put(player, player.win(dealer)));

        applyDealerResult();
    }

    public static TotalResult of(Dealer dealer, Players players) {
        return new TotalResult(dealer, players);
    }

    private void applyDealerResult() {
        playersResult.values()
                .forEach(winningResult ->
                        dealerResult.computeIfPresent(winningResult.reverse(), (key, value) -> value + 1));
    }

    public Map<Player, WinningResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<WinningResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}
