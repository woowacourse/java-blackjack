package blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int BURST_NUMBER = 21;

    private final Map<String, Integer> dealerResult;
    private final Map<String, String> playerResult;

    public GameResult(Game game) {
        this.dealerResult = initializeDealerResult();
        this.playerResult = new LinkedHashMap<>();
        accumulationResult(game);
    }

    private Map<String, Integer> initializeDealerResult() {
        Map<String, Integer> dealerResult = new HashMap<>();

        dealerResult.put(ResultType.WIN.message(), 0);
        dealerResult.put(ResultType.LOSE.message(), 0);
        dealerResult.put(ResultType.DRAW.message(), 0);
        return dealerResult;
    }

    private String getPlayerResultWhenDealerBurst(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return ResultType.WIN.message();
        }
        if (playerScore < dealerScore) {
            return ResultType.LOSE.message();
        }
        return ResultType.DRAW.message();
    }

    private String getPlayerResultWhenDealerNotBurst(int playerScore) {
        if (playerScore <= BURST_NUMBER) {
            return ResultType.WIN.message();
        }
        return ResultType.DRAW.message();
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
            return getPlayerResultWhenDealerNotBurst(playerScore);
        }
        if (playerScore <= BURST_NUMBER) {
            return getPlayerResultWhenDealerBurst(dealerScore, playerScore);
        }
        return ResultType.LOSE.message();
    }

    public Map<String, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, String> getPlayerResult() {
        return playerResult;
    }
}
