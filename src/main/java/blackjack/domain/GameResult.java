package blackjack.domain;

import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int INIT_NUMBER = 0;

    private final Map<Result, Integer> dealerResults;
    private final Map<Player, Result> playersResults;

    public GameResult(Game game) {
        this.dealerResults = initDealerResult();
        this.playersResults = new LinkedHashMap<>();
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
        Dealer dealer = game.getDealer();

        for (Player player : game.getPlayers()) {
            Result playerResult = ResultReferee.getPlayerResult(player, dealer);
            Result dealerResult = ResultReferee.getOpponentResult(playerResult);
            playersResults.put(player, playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
    }

    public Map<Result, Integer> getDealerResults() {
        return dealerResults;
    }

    public Map<Player, Result> getPlayersResults() {
        return playersResults;
    }
}
