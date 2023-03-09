package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultCalculator {

    private final Map<Name, Integer> resultOfBetting;

    public ResultCalculator(Betting betting, Map<Player, GameResult> result) {
        resultOfBetting = new LinkedHashMap<>();
        calculateResult(betting, result);
    }

    private void calculateResult(Betting betting, Map<Player, GameResult> result) {
        resultOfBetting.put(Name.DEALER_NAME, 0);
        result.forEach((player, gameResult) -> calculateEachResult(player, gameResult, betting));
    }

    private void calculateEachResult(Player player, GameResult gameResult, Betting betting) {
        int money = (int) (betting.getPlayerMoney(player) * gameResult.getRateOfReturn());
        resultOfBetting.put(player.getName(), money);
        resultOfBetting.put(Name.DEALER_NAME, calculateDealerMoney(money));
    }

    private int calculateDealerMoney(int money) {
        return resultOfBetting.getOrDefault(Name.DEALER_NAME, 0) - money;
    }

    public Map<Name, Integer> getResultOfBetting() {
        return resultOfBetting;
    }

}
