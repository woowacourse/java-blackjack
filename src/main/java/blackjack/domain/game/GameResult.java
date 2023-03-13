package blackjack.domain.game;

import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;
import blackjack.domain.gameplayer.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private static final double BLACKJACK_PRIZE_RATE = 2.5;
    private static final int PLAYER_WIN_PRIZE_RATE = 2;
    private static final int PLAYER_LOSE_PRIZE_RATE = -1;

    private final Map<Player, Integer> playerBettingResults;

    GameResult(Dealer dealer, Players players) {
        this.playerBettingResults = new LinkedHashMap<>();
        accumulationResult(dealer, players);
    }

    private void accumulationResult(Dealer dealer, Players players) {
        for (Player player : players) {
            Result playerResult = ResultReferee.getPlayerResult(player, dealer);
            calculatePrize(player, playerResult);
        }
    }

    private void calculatePrize(Player player, Result playerResult) {
        int playerBetting = calculatePlayerBetting(player.getBetting().getMoney(), playerResult);

        playerBettingResults.put(player, playerBetting);
    }

    private int calculatePlayerBetting(int playerBetting, Result playerResult) {
        if (playerResult.equals(Result.BLACKJACK)) {
            return (int) (playerBetting * BLACKJACK_PRIZE_RATE);
        }
        if (playerResult.equals(Result.WIN)) {
            return playerBetting * PLAYER_WIN_PRIZE_RATE;
        }
        if (playerResult.equals(Result.LOSE)) {
            return playerBetting * PLAYER_LOSE_PRIZE_RATE;
        }
        return playerBetting;
    }

    public Map<Player, Integer> getPlayerBettingResults() {
        return playerBettingResults;
    }

    public int getDealerBettingResults() {
        return -playerBettingResults.values().stream()
                .mapToInt(x -> x.intValue())
                .sum();
    }
}
