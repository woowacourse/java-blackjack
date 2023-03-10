package domain.game;

import domain.money.Bet;
import domain.user.Player;
import java.util.HashMap;
import java.util.Map;

public class GameBetting {
    
    private final Map<Player, Bet> betMap = new HashMap<>();
    
    public void accumulate(final Player player, final Bet bet) {
        this.betMap.put(player, bet);
    }
    
    public Map<Player, Bet> getBetMap() {
        return this.betMap;
    }
}
