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
    private final Map<BlackJackParticipant, Integer> bettingResults;

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
        bettingResults.put(dealer, 0);

        for (Player player : game.getPlayers()) {
            Result playerResult = ResultReferee.getPlayerResult(player, dealer);
            Result dealerResult = ResultReferee.getOpponentResult(playerResult);
            playersResults.put(player, playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);

            calculatePrize(dealer, player, playerResult);
        }
    }

    private void calculatePrize(Dealer dealer, Player player, Result playerResult) {
        int playerBetting = calculatePlayerBetting(player.getBetting().getBettingMoney(), playerResult);
        int dealerBetting = bettingResults.get(dealer);

        bettingResults.put(player, playerBetting);
        bettingResults.put(dealer, dealerBetting-playerBetting);
    }

    private static int calculatePlayerBetting(int playerBetting, Result playerResult) {
        if (playerResult.equals(Result.BLACKJACK)) {
            return (int) (playerBetting * 2.5);
        }
        if (playerResult.equals(Result.WIN)) {
            return playerBetting * 2;
        }
        if (playerResult.equals(Result.LOSE)) {
            return playerBetting * -1;
        }
        return playerBetting;
    }

    public Map<Result, Integer> getDealerResults() {
        return dealerResults;
    }

    public Map<Player, Result> getPlayersResults() {
        return playersResults;
    }

    public Map<BlackJackParticipant, Integer> getBettingResults() {
        return bettingResults;
    }
}
