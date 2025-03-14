package domain.game;

import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class BettingRound {
    Map<Player, Integer> initialPlayerBets = new HashMap<>();
    Map<Player, Integer> finalPlayerBets = new HashMap<>();

    public void bet(Player player, int betAmount) {
        initialPlayerBets.put(player, betAmount);
    }

    public int getPlayerInitialBetAmount(Player player) {
        return initialPlayerBets.get(player);
    }

    public int getPlayerFinalBetAmount(Player player) {
        return finalPlayerBets.get(player);
    }

    public void betIsLostIfPlayerBusts(Player player) {
        if (player.isBust()) {
            finalPlayerBets.put(player, 0);
        }
    }
}
