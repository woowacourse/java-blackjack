package domain.gameresult;

import domain.player.Bet;
import domain.player.Player;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Bet> resultOfPlayers = new HashMap<>();

//    public void saveResultWithMultiplying(Player player, float multiply) {
//        resultOfPlayers.put(player, player.getBetWithMultiplying(multiply));
//    }
}
