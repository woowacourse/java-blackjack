package blackjack.model;

import blackjack.model.player.Gamer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private int dealerResult;
    private final Map<Gamer, Profit> playersResult;

    public GameResult() {
        playersResult = new HashMap<>();
    }

    public void updatePlayerBettingResult(Gamer gamer, Result result) {
        playersResult.put(gamer, calculateProfit(gamer, result));
    }

    private Profit calculateProfit(Gamer gamer, Result result) {
        if (isPossibleToGetAdditionalMoney(gamer, result)) {
            return Profit.of(gamer.getBetting(), result, true);
        }
        return Profit.of(gamer.getBetting(), result, false);
    }

    private boolean isPossibleToGetAdditionalMoney(Gamer gamer, Result result) {
        return result == Result.WIN && gamer.isBlackjack();
    }

    public void calculateDealerResult() {
        for (Profit money : playersResult.values()) {
            dealerResult -= money.getAmount();
        }
    }

    public int getDealerResult() {
        return dealerResult;
    }

    public Map<Gamer, Profit> getPlayersResult() {
        return new LinkedHashMap<>(playersResult);
    }
}
