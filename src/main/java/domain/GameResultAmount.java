package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultAmount {

    private static final int INITIAL_AMOUNT = 0;
    private final Map<Name, ResultAmount> resultOfBetting;

    public GameResultAmount(Map<Player, GameResult> result) {
        resultOfBetting = new LinkedHashMap<>();
        calculateResult(result);
    }

    private void calculateResult(Map<Player, GameResult> result) {
        resultOfBetting.put(Dealer.DEALER_NAME, new ResultAmount(INITIAL_AMOUNT));
        result.forEach(this::calculateEachResult);
    }

    private void calculateEachResult(Player player, GameResult gameResult) {
        int amount = player.calculateResultAmount(gameResult);
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
