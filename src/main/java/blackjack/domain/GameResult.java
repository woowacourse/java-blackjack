package blackjack.domain;

import blackjack.domain.gameplayer.Betting;
import blackjack.domain.gameplayer.BlackJackParticipant;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int INIT_NUMBER = 0;

    private final Map<Result, Integer> dealerResults;
    private final Map<Player, Result> playersResults;
    private final Map<BlackJackParticipant, Betting> bettingResults;

    public GameResult(Game game) {
        this.dealerResults = initDealerResult();
        this.playersResults = new HashMap<>();
        this.bettingResults = new HashMap<>();
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
        bettingResults.put(dealer, Betting.defaultBetting);

        for (Player player : game.getPlayers()) {
            Result playerResult = ResultReferee.getPlayerResult(player, dealer);
            playersResults.put(player, playerResult);

            Result dealerResult = ResultReferee.getOpponentResult(playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);

            Betting playerBetting = player.getBetting();
            if (playerResult.equals(Result.BLACKJACK)) {

            }
        }
    }

    public Map<Result, Integer> getDealerResults() {
        return dealerResults;
    }

    public Map<Player, Result> getPlayersResults() {
        return playersResults;
    }
}
