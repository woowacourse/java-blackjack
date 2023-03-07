package blackjack.domain;

import blackjack.domain.gameplayer.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int INIT_NUMBER = 0;

    private final Map<Result, Integer> dealerResult;
    private final Map<Player, Result> playerResult;

    public GameResult(Game game) {
        this.dealerResult = initDealerResult();
        this.playerResult = new LinkedHashMap<>();
        accumulationResult(game);
    }

    private static Map<Result, Integer> initDealerResult() {
        Map<Result, Integer> dealerResult = new HashMap<>();

        for (Result result : Result.values()) {
            dealerResult.put(result, INIT_NUMBER);
        }
        return dealerResult;
    }

    private void accumulationResult(Game game) {
        Score dealerScore = game.getDealerScore();

        for (Player player : game.getPlayers()) {
            Score playerScore = player.calculateScore();
            Result playerWin = Result.getLeftResult(playerScore, dealerScore, Score.BUST_LOWER_BOUND);
            Result dealerWin = Result.getOpponentResult(playerWin);
            playerResult.put(player, playerWin);
            dealerResult.put(dealerWin, dealerResult.get(dealerWin) + 1);
        }
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Player, Result> getPlayerResult() {
        return playerResult;
    }
}
