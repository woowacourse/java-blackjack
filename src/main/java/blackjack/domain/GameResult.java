package blackjack.domain;

import blackjack.domain.gameplayer.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BURST_NUMBER = 21;

    private final Map<Result, Integer> dealerResult;
    private final Map<String, Result> playerResult;

    public GameResult(Game game) {
        this.dealerResult = initDealerResult();
        this.playerResult = new LinkedHashMap<>();
        accumulationResult(game);
    }

    private static Map<Result, Integer> initDealerResult() {
        Map<Result, Integer> dealerResult = new HashMap<>();

        for (Result result: Result.values()) {
            dealerResult.put(result, 0);
        }
        return dealerResult;
    }

    private void accumulationResult(Game game) {
        int dealerScore = game.getDealerScore();

        for (Player player : game.getPlayers()) {
            int playerScore = player.calculateScore();
            Result playerWin = Result.getLeftResult(playerScore, dealerScore, BURST_NUMBER);
            Result dealerWin = Result.getLeftResult(dealerScore, playerScore, BURST_NUMBER);
            playerResult.put(player.showName(), playerWin);
            dealerResult.put(dealerWin, dealerResult.get(dealerWin) + 1);
        }
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, Result> getPlayerResult() {
        return playerResult;
    }
}
