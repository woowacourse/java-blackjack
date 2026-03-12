package domain.participant;

import static domain.constant.GameRule.DEALER_HIT_CRITERION;

import domain.enums.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    @Override
    public boolean checkScoreUnderCriterion() {
        return cardBoard.calculateScore() < DEALER_HIT_CRITERION;
    }

    public Map<Result, Integer> calculateResults(List<Result> playerResults) {
        Map<Result, Integer> results = new HashMap<>();
        playerResults.forEach(
                result -> {
                    Result dealerResult = Result.getOpposite(result);
                    results.put(dealerResult, results.getOrDefault(dealerResult, 0) + 1);
                }
        );
        return results;
    }
}
