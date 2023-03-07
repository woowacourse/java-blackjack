package blackjack.domain;

import blackjack.domain.gameplayer.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BURST_NUMBER = 21;
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DRAW = "무";
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

    private static String getPlayerWinWhenDealerLessThan21(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    private static String getPlayerWinWhenDealerOverThan21(int playerScore) {
        if (playerScore <= BURST_NUMBER) {
            return WIN;
        }
        return DRAW;
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

    private String getPlayerWin(int dealerScore, int playerScore) {
        if (dealerScore > BURST_NUMBER) {
            return getPlayerWinWhenDealerOverThan21(playerScore);
        }
        if (playerScore <= BURST_NUMBER) {
            return getPlayerWinWhenDealerLessThan21(dealerScore, playerScore);
        }
        return LOSE;
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, Result> getPlayerResult() {
        return playerResult;
    }
}
