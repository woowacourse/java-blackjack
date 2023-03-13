package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultAmount {

    private static final int INITIAL_AMOUNT = 0;
    private final Map<Name, ResultAmount> resultOfBetting;

    public GameResultAmount(Map<Player, GameResult> result, Map<Player, Amount> bettingAmount) {
        resultOfBetting = new LinkedHashMap<>();
        calculateResult(result, bettingAmount);
    }

    private void calculateResult(Map<Player, GameResult> result, Map<Player, Amount> bettingAmount) {
        resultOfBetting.put(Dealer.DEALER_NAME, new ResultAmount(INITIAL_AMOUNT));
        result.forEach((player, gameResult) -> calculateEachResult(player, bettingAmount, gameResult));
    }

    private void calculateEachResult(Player player, Map<Player, Amount> bettingAmount, GameResult gameResult) {
        int amount = (int) (bettingAmount.get(player).getValue() * gameResult.getRateOfReturn());
        resultOfBetting.put(player.getName(), new ResultAmount(amount));
        resultOfBetting.put(Dealer.DEALER_NAME, calculateDealerAmount(amount));
    }

    private ResultAmount calculateDealerAmount(int amount) {
        return resultOfBetting.get(Dealer.DEALER_NAME).minus(amount);
    }

    public Map<Name, ResultAmount> getResultOfBetting() {
        return resultOfBetting;
    }

}
