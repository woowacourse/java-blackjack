package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayersResult {
    Map<Player, PlayerResult> playerResults;

    public PlayersResult(Players players, Dealer dealer) {
        this.playerResults = calculateGameResults(players, dealer);
    }

    public Map<Player, PlayerResult> getPlayerResults() {
        return Collections.unmodifiableMap(playerResults);
    }

    private Map<Player, PlayerResult> calculateGameResults(Players players, Dealer dealer) {
        List<Player> playerList = players.getPlayers();
        Map<Player, PlayerResult> playersResultWithOutDealer = new LinkedHashMap<>();

        for(Player player : playerList) {
            playersResultWithOutDealer.put(player,dealer.compare(player));
        }

        return playersResultWithOutDealer;
    }
}
