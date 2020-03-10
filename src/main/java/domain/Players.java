package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private List<Player> players;

    public Players(List<Player> players){
        this.players = players;
    }

    public Map<User,Boolean> getSummary(Dealer dealer) {
        Map<User,Boolean> summary = new HashMap<>();
        for(Player player : players){
            summary.put(player,player.isWin(dealer));
        }
        return summary;
    }
}
