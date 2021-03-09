package blackjack.domain.result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Results {

    private final List<Result> results;

    public Results() {
        results = new ArrayList<>();
    }

    public void addResult(Result playerResult) {
        results.add(playerResult);
    }

    public List<Result> findDealerResult() {
        return results.stream()
                .map(this::convertResult)
                .collect(Collectors.toList());
    }

    private Result convertResult(Result playerResult) {
        if (playerResult == Result.DRAW) {
            return Result.DRAW;
        }

        if (playerResult == Result.LOSE) {
            return Result.WIN;
        }

        return Result.LOSE;
    }

    public List<Result> findPlayerResult() {
        return results;
    }
}
