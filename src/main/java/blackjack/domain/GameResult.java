package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Outcome, Integer> dealerResult;
    private final Map<String, Outcome> playersResult;

    private GameResult(Map<Outcome, Integer> dealerResult, Map<String, Outcome> playersResult) {
        this.dealerResult = Collections.unmodifiableMap(new EnumMap<>(dealerResult));
        this.playersResult = Collections.unmodifiableMap(new LinkedHashMap<>(playersResult));
    }

    public static GameResult of(Dealer dealer, List<Player> players) {
        Map<Outcome, Integer> dealerResult = new EnumMap<>(Outcome.class);
        Map<String, Outcome> playersResult = new LinkedHashMap<>();

        for (Player player : players) {
            Outcome playerOutcome = Outcome.judge(player, dealer);
            playersResult.put(player.getName(), playerOutcome);
            putDealerResult(dealerResult, playerOutcome);
        }

        return new GameResult(dealerResult, playersResult);
    }

    private static void putDealerResult(Map<Outcome, Integer> dealerResult, Outcome playerOutcome) {
        dealerResult.merge(playerOutcome.getOpposite(), 1, Integer::sum);
    }

    public Map<Outcome, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, Outcome> getPlayersResult() {
        return playersResult;
    }
}
