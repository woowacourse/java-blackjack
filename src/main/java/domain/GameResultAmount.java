package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultAmount {

    private final Map<Name, Integer> resultOfBetting;
    private final Name DEALER_NAME = new Name("딜러");

    public GameResultAmount(Betting betting, Map<Name, GameResult> result) {
        resultOfBetting = new LinkedHashMap<>();
        calculateResult(betting, result);
    }

    private void calculateResult(Betting betting, Map<Name, GameResult> result) {
        resultOfBetting.put(DEALER_NAME, 0);
        result.forEach((name, gameResult) -> calculateEachResult(name, gameResult, betting));
    }

    private void calculateEachResult(Name name, GameResult gameResult, Betting betting) {
        int amount = (int) (betting.getAmountFromPlayerName(name) * gameResult.getRateOfReturn());
        resultOfBetting.put(name, amount);
        resultOfBetting.put(DEALER_NAME, calculateDealerAmount(amount));
    }

    private int calculateDealerAmount(int money) {
        return resultOfBetting.getOrDefault(DEALER_NAME, 0) - money;
    }

    public Map<Name, Integer> getResultOfBetting() {
        return resultOfBetting;
    }

}
