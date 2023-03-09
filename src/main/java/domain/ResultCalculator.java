package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultCalculator {

    private final Map<Name, Integer> resultOfBetting;

    public ResultCalculator(Map<Name, Integer> betting, Map<Name, GameResult> result) {
        resultOfBetting = new LinkedHashMap<>();
        calculateResult(betting, result);
    }

    private void calculateResult(Map<Name, Integer> betting, Map<Name, GameResult> result) {
        betting.forEach((name, money) -> calculateEachResult(name, money, result));
    }

    private void calculateEachResult(Name name, int money, Map<Name, GameResult> result) {
        money *= result.get(name)
                .getRateOfReturn();
        resultOfBetting.put(name, money);
        resultOfBetting.put(Name.DEALER_NAME, calculateDealerMoney(money));
    }

    private int calculateDealerMoney(int money) {
        return resultOfBetting.getOrDefault(Name.DEALER_NAME, 0) - money;
    }

    public Map<Name, Integer> getResultOfBetting() {
        return resultOfBetting;
    }

}
