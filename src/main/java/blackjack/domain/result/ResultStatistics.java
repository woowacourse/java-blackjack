package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultStatistics {

    private final Map<Player, Result> resultStatistics = new LinkedHashMap<>();

    public ResultStatistics(final Players players, final Dealer dealer) {
        players.getPlayersAsList()
                .forEach(player -> resultStatistics.put(player, Result.getPlayerResult(player, dealer)));
    }

    public Map<Player, Result> getResultStatistics() {
        return resultStatistics;
    }

    public int getDealerWinCounts() {
        return calculateDealerResult(Result.LOSE);
    }

    public int getDealerDrawCounts() {
        return calculateDealerResult(Result.DRAW);
    }

    public int getDealerLoseCounts() {
        return calculateDealerResult(Result.WIN);
    }

    private int calculateDealerResult(final Result result) {
        return Collections.frequency(resultStatistics.values(), result);
    }
}
