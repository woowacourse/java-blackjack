package blackjack.domain;

import blackjack.domain.gameplayer.BlackJackParticipant;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final int INIT_NUMBER = 0;

    private final Map<Result, Integer> dealerResults;
    private final Map<Player, Result> playersResults;
    private final Map<Player, Integer> playerBettingResults;

    private int dealerBettingResults;

    public GameResult(Game game) {
        this.dealerResults = initDealerResult();
        this.playersResults = new LinkedHashMap<>();
        this.playerBettingResults = new LinkedHashMap<>();
        dealerBettingResults = 0;
        accumulationResult(game);
    }

    private static Map<Result, Integer> initDealerResult() {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>();

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

            calculatePrize(player, playerResult);
        }
    }

    private void calculatePrize(Player player, Result playerResult) {
        int playerBetting = calculatePlayerBetting(player.getBetting().getBettingMoney(), playerResult);

        playerBettingResults.put(player, playerBetting);
        dealerBettingResults -= playerBetting;
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

    public Map<Player, Integer> getPlayerBettingResults() {
        return playerBettingResults;
    }

    public int getDealerBettingResults() {
        return dealerBettingResults;
    }
}
