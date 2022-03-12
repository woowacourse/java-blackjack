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

    public WinResult(Dealer dealer, List<Player> players) {
        dealerResult = new EnumMap<>(Outcome.class);
        playersResult = new LinkedHashMap<>();

        initDealerResult();
        calculateResult(dealer, List.copyOf(players));
    }

    private void initDealerResult() {
        Arrays.stream(Outcome.values())
                .forEach(value -> dealerResult.put(value, 0));
    }

    private void calculateResult(Dealer dealer, List<Player> players) {
        players.forEach(player -> updateResult(player, Rule.INSTANCE.judgeOutcome(player, dealer)));
    }

    private void updateResult(Player player, Outcome playerOutcome) {
        dealerResult.merge(playerOutcome.getOpposite(), 1, Integer::sum);
        playersResult.put(player.getName(), playerOutcome);
    }

    public Map<Outcome, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    public Map<String, Outcome> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }
}
