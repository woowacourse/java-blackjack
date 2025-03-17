package model.betting;

import java.util.Map;
import model.participant.Player;

public class PlayersBetting {
    private final Map<Player, Betting> playerBets ;

    public PlayersBetting(Map<Player, Betting> playerBets) {
        this.playerBets  = playerBets ;
    }

    public Betting findPlayerBet(Player player){
        return playerBets.get(player);
    }
}
