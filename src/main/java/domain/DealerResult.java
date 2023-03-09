package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealerResult {
    private final Map<Player, Result> resultAgainstPlayers;

    private DealerResult(Map<Player, Result> resultAgainstPlayers) {
        this.resultAgainstPlayers = resultAgainstPlayers;
    }

    public static DealerResult from(BlackjackGame blackjackGame) {
        Map<Player, Result> resultAgainstPlayers = new LinkedHashMap<>();
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();

        for (Player player : players.getPlayers()) {
            Result dealerResult = dealer.competeWithPlayer(player);
            resultAgainstPlayers.put(player, dealerResult);
        }

        return new DealerResult(resultAgainstPlayers);
    }

    public int getResultCount(Result result) {
        return (int) resultAgainstPlayers.values().stream()
                .filter(result::equals)
                .count();
    }

    public Map<Player, Result> getDealerResultsAgainstParticipants() {
        return Collections.unmodifiableMap(resultAgainstPlayers);
    }
}
