package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinResult {

    private final Map<Outcome, Integer> dealerResult;
    private final Map<String, Outcome> playersResult;

    private WinResult(Map<Outcome, Integer> dealerResult, Map<String, Outcome> playersResult) {
        this.dealerResult = Collections.unmodifiableMap(new EnumMap<>(dealerResult));
        this.playersResult = Collections.unmodifiableMap(new LinkedHashMap<>(playersResult));
    }

    public static WinResult of(Dealer dealer, List<Player> players) {
        Map<Outcome, Integer> dealerResult = new EnumMap<>(Outcome.class);
        Map<String, Outcome> playersResult = new LinkedHashMap<>();

        initDealerResult(dealerResult);
        for (Player player : players) {
            Outcome playerOutcome = Outcome.judge(player, dealer);
            playersResult.put(player.getName(), playerOutcome);
            dealerResult.merge(playerOutcome.getOpposite(), 1, Integer::sum);
        }

        return new WinResult(dealerResult, playersResult);
    }

    private static void initDealerResult(Map<Outcome, Integer> dealerResult) {
        Arrays.stream(Outcome.values())
                .forEach(value -> dealerResult.put(value, 0));
    }

    public Map<Outcome, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, Outcome> getPlayersResult() {
        return playersResult;
    }
}
