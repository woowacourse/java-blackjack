package blackjack.domain.player;

import blackjack.domain.Result.Result;
import blackjack.domain.Result.Results;

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

    public List<Result> getDealerResults() {
        return results.findDealerResult();
    }

    public List<Result> getPlayerResults() {
        return results.findPlayerResult();
    }
}
