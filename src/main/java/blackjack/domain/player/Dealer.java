package blackjack.domain.player;

import blackjack.domain.Result;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private List<Result> results;

    public Dealer() {
        this("null");
    }

    public Dealer(String name) {
        super(name);
        this.results = new ArrayList<>();
    }

    public Result compare(Player player) {
        Result result = Result.of(this.isBlackjack(), player.isBlackjack());

        if (result != Result.NONE) {
            return result;
        }

        result = Result.of(getCards().getScore(), player.getScore());
        addResult(result);

        return result;
    }

    private void addResult(Result playerResult) {
        if (playerResult == Result.DRAW) {
            results.add(Result.DRAW);
        }

        if (playerResult == Result.LOSE) {
            results.add(Result.WIN);
        }

        if (playerResult == Result.WIN || playerResult == Result.BLACKJACK_WIN) {
            results.add(Result.LOSE);
        }
    }

    public List<Result> getResults() {
        return results;
    }

    public boolean canDrawOneMore(int score) {
        return score <= 16;
    }

    public int findResultCount(Result targetResult) {
        return (int) results.stream()
                .filter(result -> result == targetResult)
                .count();
    }
}
