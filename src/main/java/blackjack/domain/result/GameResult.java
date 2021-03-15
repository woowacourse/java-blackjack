package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, Result> gameResult = new LinkedHashMap<>();

    public GameResult(final List<Player> players, final Dealer dealer) {
        players.forEach(player -> gameResult.put(player, Result.evaluate(player, dealer)));
    }

    public Map<Player, Result> getGameResult() {
        return new LinkedHashMap<>(gameResult);
    }

    public int getDealerWinCounts() {
        return calculateDealerResult(Result.LOSE);
    }

    public int getDealerDrawCounts() {
        return calculateDealerResult(Result.DRAW);
    }

    public int getDealerLoseCounts() {
        return calculateDealerResult(Result.WIN) + calculateDealerResult(Result.BLACKJACK_WIN);
    }

    public double calculateDealerProfit() {
        return -gameResult.keySet()
                .stream().mapToDouble(player -> (int) player.getProfit()).sum();
    }

    private int calculateDealerResult(final Result result) {
        return Collections.frequency(gameResult.values(), result);
    }
}
