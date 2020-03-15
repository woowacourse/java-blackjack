package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayersResult {
    Map<Player, PlayerResult> playerResults;

    public PlayersResult(Players players, Dealer dealer) {
        this.playerResults = calculateGameResults(players, dealer);
    }

    public int dealerWinCount() {
        return resultCount(playerResults, PlayerResult.LOSE);
    }

    public int dealerDrawCount() {
        return resultCount(playerResults, PlayerResult.DRAW);
    }

    public int dealerLoseCount() {
        return resultCount(playerResults, PlayerResult.WIN);
    }

    private int resultCount(Map<Player, PlayerResult> playerResults, PlayerResult result) {
        int resultCount = 0;
        for (Player player : playerResults.keySet()) {
            if (playerResults.get(player) == result){
                resultCount++;
            }
        }
        return resultCount;
    }

    private Map<Player, PlayerResult> calculateGameResults(Players players, Dealer dealer) {
        List<Player> playerExceptDealer = new ArrayList<>();
        for(int playerIndex = 0; playerIndex < players.participantNumber(); playerIndex++) {
            playerExceptDealer.add(players.eachPlayer(playerIndex));
        }
        Map<Player, PlayerResult> playersResultWithOutDealer= new HashMap<>();
        for(Player player : playerExceptDealer) {
            playersResultWithOutDealer.put(player,PlayerResult.match(dealer, player));
        }
        return playersResultWithOutDealer;
    }

    public String playerGameResult(Player player) {
        return player.getName() + " : " + playerResults.get(player).getResultState();
    }
}
