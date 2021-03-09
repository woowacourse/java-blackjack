package blackjack.domain.player;

import blackjack.domain.result.Result;
import blackjack.domain.result.Results;

import java.util.List;

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
        results.addResult(result);

        return result;
    }

    public boolean canDrawOneMore() {
        return getScore() <= DEALER_DRAW_LIMIT;
    }

    public int countOfResult(Result result) {
        return results.findResultCountOfDealer(result);
    }

    public float profit(List<Player> players) {
        float profit = 0;

        for (int i = 0; i < players.size(); i++) {
            profit -= players.get(i).profit(getPlayerResult(i));
        }

        return profit;
    }

    public List<Result> getDealerResults() {
        return results.findDealerResult();
    }

    public List<Result> getPlayerResults() {
        return results.findPlayerResult();
    }

    public Result getPlayerResult(int index) {
        return results.findPlayerResult().get(index);
    }
}
