package blackjack.domain.player;

import blackjack.domain.Result;
import blackjack.domain.Results;

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
        Result result = Result.of(this.isBlackjack(), player.isBlackjack());

        if (result != Result.NONE) {
            results.addResult(result);
            return result;
        }

        result = Result.of(getScore(), player.getScore());
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
