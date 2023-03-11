package blackjack.domain;

import blackjack.domain.gameplayer.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    public static final Score bustLowerBound = Score.of(22);

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
        Score dealerScore = game.getDealer().calculateScore();

        for (Player player : game.getPlayers()) {
            Score playerScore = player.calculateScore();
            Result playerWin = getPlayerResult(dealerScore, player, playerScore);
            Result dealerWin = Result.getOpponentResult(playerWin);
            playerResult.put(player, playerWin);
            dealerResult.put(dealerWin, dealerResult.get(dealerWin) + 1);
        }
    }

    private Result getPlayerResult(Score dealerScore, Player player, Score playerScore) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        return Result.getLeftResult(playerScore, dealerScore, bustLowerBound);
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Player, Result> getPlayerResult() {
        return playerResult;
    }
}
