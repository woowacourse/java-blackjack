package domain;

import domain.enums.Result;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private final Map<Result, Integer> results = new HashMap<>();

    public Dealer() {
        Arrays.stream(Result.values())
                .forEach(result -> this.results.put(result, 0));
    }

    @Override
    public boolean checkScoreUnderCriterion() {
        return cardBoard.calculateScore() < 17;
    }

    public void addResults(List<Result> playerResults) {
        playerResults.forEach(
                result -> {
                    Result dealerResult = Result.getOpposite(result);
                    results.put(dealerResult, results.get(dealerResult) + 1);
                }
        );
    }

    public Map<Result, Integer> getResult() {
        return Map.copyOf(results);
    }
}
