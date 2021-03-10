package blackjack.domain.player;

import blackjack.domain.result.Result;
import blackjack.domain.result.Results;

import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int DEALER_DRAW_LIMIT = 16;

    private final Results results;

    public Dealer() {
        this("null");
    }

    public Dealer(String name) {
        super(name);
        this.results = new Results();
    }

    public Result compare(Player player) {
        Result result = Result.of(this, player);
        results.addResult(player, result);

        return result;
    }

    public boolean canDrawOneMore() {
        return getScore() <= DEALER_DRAW_LIMIT;
    }

    public double profit(List<Player> players) {
        double profit = 0;

        for (Player player : players) {
            profit -= player.profit(getPlayerResult(player));
        }

        return profit;
    }

    public Map<Result, Integer> getDealerResults() {
        return results.findDealerResult();
    }

    public Result getPlayerResult(Player player) {
        return results.getResults(player);
    }
}
