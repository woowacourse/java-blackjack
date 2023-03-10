package domain.game;

import domain.money.Bet;
import domain.user.Player;
import java.util.HashMap;
import java.util.Map;

public class GameBet {
    
    private final Map<Player, Bet> betMap = new HashMap<>();
    
    public void accumulate(Player player, Bet bet) {
        this.betMap.put(player, bet);
    }
    
    public Map<Player, Bet> getBetMap() {
        return this.betMap;
    }
    
    public Bet getPlayerBet(Player player) {
        return this.betMap.get(player);
    }
}
