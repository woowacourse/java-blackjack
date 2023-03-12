package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultCalculator {

    private final Map<Name, Integer> resultOfBetting;
    private final Name DEALER_NAME = new Name("딜러");

    public ResultCalculator(Betting betting, Map<Name, GameResult> result) {
        resultOfBetting = new LinkedHashMap<>();
        calculateResult(betting, result);
    }

    private void calculateResult(Betting betting, Map<Name, GameResult> result) {
        resultOfBetting.put(DEALER_NAME, 0);
        result.forEach((name, gameResult) -> calculateEachResult(name, gameResult, betting));
    }

    private void calculateEachResult(Name name, GameResult gameResult, Betting betting) {
        int money = (int) (betting.getMoneyFromPlayerName(name) * gameResult.getRateOfReturn());
        resultOfBetting.put(name, money);
        resultOfBetting.put(DEALER_NAME, calculateDealerMoney(money));
    }

    private int calculateDealerMoney(int money) {
        return resultOfBetting.getOrDefault(DEALER_NAME, 0) - money;
    }

    public Map<Name, Integer> getResultOfBetting() {
        return resultOfBetting;
    }

}
