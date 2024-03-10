package domain;

import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Judge {

    private final Map<Player, WinState> playerResult;
    private final Map<WinState, Integer> dealerResult;

    public Judge() {
        this.playerResult = new LinkedHashMap<>();
        this.dealerResult = new LinkedHashMap<>();
    }

    public void decideResult(Gamers gamers) {
        decidePlayersResult(gamers.getPlayers(), gamers.getDealer());
        decideDealerResult();
    }

    private void decidePlayersResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            decidePlayerResult(player, dealer);
        }
    }

    private void decidePlayerResult(Player player, Dealer dealer) {
        if (player.isBust()) {
            playerResult.put(player, WinState.LOSE);
            return;
        }
        if (dealer.isBust()) {
            playerResult.put(player, WinState.WIN);
            return;
        }
        WinState playerWinState = decidePlayerWinState(player, dealer);
        playerResult.put(player, playerWinState);
    }

    private WinState decidePlayerWinState(Player player, Dealer dealer) {
        int playerScore = player.finalizeCardsScore();
        int dealerScore = dealer.finalizeCardsScore();
        if (playerScore > dealerScore) {
            return WinState.WIN;
        }
        if (playerScore < dealerScore) {
            return WinState.LOSE;
        }
        return WinState.DRAW;
    }

    private void decideDealerResult() {
        dealerResult.put(WinState.WIN, countWinStateOfPlayerResult(WinState.LOSE));
        dealerResult.put(WinState.DRAW, countWinStateOfPlayerResult(WinState.DRAW));
        dealerResult.put(WinState.LOSE, countWinStateOfPlayerResult(WinState.WIN));
    }

    private int countWinStateOfPlayerResult(WinState winState) {
        return (int) playerResult.values().stream()
                .filter(value -> value.equals(winState))
                .count();
    }

    public Map<Player, WinState> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }

    public Map<WinState, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}
