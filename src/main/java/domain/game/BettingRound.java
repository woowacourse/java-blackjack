package domain.game;

import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class BettingRound {
    Map<Player, Integer> playerBets = new HashMap<>();

    public void bet(Player player, int betAmount) {
        playerBets.put(player, betAmount);
    }

    public int getPlayerBetAmount(Player player) {
        return playerBets.get(player);
    }
}
