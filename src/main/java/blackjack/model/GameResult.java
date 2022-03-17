package blackjack.model;

import blackjack.model.player.Gamer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private int dealerResult;
    private final Map<Gamer, Integer> playersResult;

    public GameResult() {
        playersResult = new HashMap<>();
    }

    public void updatePlayerBettingResult(Gamer gamer, Result result) {
        playersResult.put(gamer, calculatePlayerBettingMoney(gamer, result));
    }

    private Integer calculatePlayerBettingMoney(Gamer gamer, Result result) {
        if (isPossibleToGetAdditionalMoney(gamer, result)) {
            return (int) (gamer.getBetting().getAmount() * 1.5);
        }
        if (gamer.isBlackjack() && result == Result.DRAW) {
            return gamer.getBetting().getAmount();
        }
        return calculateBettingResult(gamer.getBetting(), result);
    }

    private boolean isPossibleToGetAdditionalMoney(Gamer gamer, Result result) {
        return result == Result.WIN && gamer.isBlackjack() && gamer.getCardSize() == 2;
    }

    private Integer calculateBettingResult(Betting bettingMoney, Result result) {
        if (result == Result.WIN) {
            return bettingMoney.getAmount();
        }
        if (result == Result.LOSE) {
            return -bettingMoney.getAmount();
        }
        return 0;
    }

    public void calculateDealerResult() {
        for (Integer money : playersResult.values()) {
            dealerResult -= money;
        }
    }

    public int getDealerResult() {
        return dealerResult;
    }

    public Map<Gamer, Integer> getPlayersResult() {
        return new LinkedHashMap<>(playersResult);
    }
}
