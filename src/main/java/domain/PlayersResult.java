package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.*;
import java.util.stream.Collectors;

public class PlayersResult {
    Map<Player, PlayerResult> playerResults;

    public PlayersResult(Players players, Dealer dealer) {
        this.playerResults = calculateGameResults(players, dealer);
    }

    public int dealerProfit() {
        int dealerEarnMoney = 0;
        Players players = getPlayersResult();
        for (Player player : players.getPlayers()) {
            dealerEarnMoney += player.getPlayerBettingMoney();
        }
        return -dealerEarnMoney;
    }

    public Players getPlayersResult() {
        return playersProfit(playerResults);
    }

    private Players playersProfit(Map<Player, PlayerResult> playerResult) {
        List<Player> playerList = new ArrayList<>();

        for (Player player : playerResult.keySet()) {
            Player afterBettingPlayer = player.money(calculatePlayerProfit(playerResult, player));
            playerList.add(afterBettingPlayer);
        }

        return new Players(playerList);
    }

    private int calculatePlayerProfit(Map<Player, PlayerResult> playerResult, Player player) {
        return (int) (player.getPlayerBettingMoney() * playerResult.get(player).getResultState());
    }

    private Map<Player, PlayerResult> calculateGameResults(Players players, Dealer dealer) {
        List<Player> playerList = players.getPlayers();
        Map<Player, PlayerResult> playersResultWithOutDealer= new HashMap<>();

        for(Player player : playerList) {
            playersResultWithOutDealer.put(player,PlayerResult.match(dealer, player));
        }

        return playersResultWithOutDealer;
    }
}
