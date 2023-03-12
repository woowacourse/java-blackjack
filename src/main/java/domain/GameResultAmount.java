package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultAmount {

    private final Map<Name, Integer> resultOfBetting;
    private final Name DEALER_NAME = new Name("딜러");

    public GameResultAmount(Map<Player, GameResult> result) {
        resultOfBetting = new LinkedHashMap<>();
        calculateResult(result);
    }

    private void calculateResult(Map<Player, GameResult> result) {
        resultOfBetting.put(DEALER_NAME, 0);
        result.forEach(this::calculateEachResult);
    }

    private void calculateEachResult(Player player, GameResult gameResult) {
        int amount = (int) (player.getAmount() * gameResult.getRateOfReturn());
        resultOfBetting.put(player.getName(), amount);
        resultOfBetting.put(DEALER_NAME, calculateDealerAmount(amount));
    }

    private int calculateDealerAmount(int money) {
        return resultOfBetting.getOrDefault(DEALER_NAME, 0) - money;
    }

    public Map<Name, Integer> getResultOfBetting() {
        return resultOfBetting;
    }

}
