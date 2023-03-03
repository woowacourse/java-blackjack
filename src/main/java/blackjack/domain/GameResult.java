package blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BURST_NUMBER = 21;
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final String DRAW = "무";
    private final Map<String, Integer> dealerResult;
    private final Map<String, String> playerResult;

    public GameResult(Game game) {
        this.dealerResult = initDealerResult();
        this.playerResult = new LinkedHashMap<>();
        accumulationResult(game);
    }

    private static Map<String, Integer> initDealerResult() {
        Map<String, Integer> dealerResult = new HashMap<>();

        dealerResult.put(WIN, 0);
        dealerResult.put(LOSE, 0);
        dealerResult.put(DRAW, 0);
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

        for (int i = 0; i < game.getPlayersCount(); i++) {
            int playerScore = game.getPlayerScoreByIndex(i);
            String playerWin = getPlayerWin(dealerScore, playerScore);
            playerResult.put(game.showPlayerNameByIndex(i), playerWin);
            dealerResult.put(playerWin, dealerResult.get(playerWin) + 1);
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

    public Map<String, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, String> getPlayerResult() {
        return playerResult;
    }
}
