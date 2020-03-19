package blackjack.domain.result;

import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Players;
import blackjack.domain.gambler.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameResult {

    private static final int INITIAL_COUNT = 0;
    private static final Map<PlayerOutcome, Integer> initialDealerResults;
    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";

    static {
        Map<PlayerOutcome, Integer> dealerResults = new LinkedHashMap<>();
        List<PlayerOutcome> dealerPlayerOutcomes = Arrays.asList(PlayerOutcome.values());
        Collections.reverse(dealerPlayerOutcomes);
        for (PlayerOutcome playerOutcome : dealerPlayerOutcomes) {
            dealerResults.put(playerOutcome, INITIAL_COUNT);
        }
        initialDealerResults = dealerResults;
    }

    private final Map<Player, PlayerOutcome> playerResults;
    private final Map<PlayerOutcome, Integer> dealerResults;

    public GameResult(Dealer dealer, Players players) {
        validNotNull(players);
        this.playerResults = Collections.unmodifiableMap(calculatePlayerResults(dealer, players));
        this.dealerResults = Collections.unmodifiableMap(calculateDealerResults());
    }

    private void validNotNull(Players players) {
        if (players == null) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    private Map<Player, PlayerOutcome> calculatePlayerResults(Dealer dealer, Players players) {
        return players.getPlayers().stream()
                .collect(Collectors
                        .toMap(player -> player, player -> player.calculateOutcome(dealer), (a1, a2) -> a1,
                                LinkedHashMap::new));
    }

    private Map<PlayerOutcome, Integer> calculateDealerResults() {
        Map<PlayerOutcome, Integer> dealerResults = initialDealerResults;
        for (PlayerOutcome playerOutcome : this.playerResults.values()) {
            dealerResults.put(playerOutcome, dealerResults.get(playerOutcome) + 1);
        }
        return dealerResults;
    }

    public Map<Player, PlayerOutcome> getPlayersResult() {
        return playerResults;
    }

    public Map<PlayerOutcome, Integer> getDealerResultsNoZero() {
        return Collections.unmodifiableMap(dealerResults.keySet().stream()
                .filter(outcome -> dealerResults.get(outcome) != 0)
                .collect(Collectors.toMap(outcome -> outcome, dealerResults::get)));
    }
}
