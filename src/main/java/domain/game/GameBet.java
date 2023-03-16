package domain.game;

import domain.money.Bet;
import domain.user.Playable;
import java.util.HashMap;
import java.util.Map;

public class GameBet {
    
    private final Map<Playable, Bet> betMap = new HashMap<>();
    
    public void accumulate(Playable player, Bet bet) {
        this.betMap.put(player, bet);
    }
    
    public Map<Playable, Bet> getBetMap() {
        return this.betMap;
    }
    
    public Bet getPlayerBet(Playable player) {
        return this.betMap.get(player);
    }
}
