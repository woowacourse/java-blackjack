package domain.score;

import domain.player.Bet;
import domain.player.Name;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private final Revenue dealerRevenue = new Revenue();
    private final Map<Name, Bet> playerRevenue;

    public ScoreBoard(Map<Name, Bet> playerRevenue) {
        this.playerRevenue = new HashMap<>(playerRevenue);
    }

    public void updatePlayerScore(Name name, Status status) {
        Bet bet = playerRevenue.get(name);
        Bet revenue = status.calculateRevenue(bet);
        playerRevenue.put(name, revenue);
    }

    public void calculateDealerRevenue() {
        dealerRevenue.calculate(playerRevenue.values().stream().toList());
    }

    public Revenue getDealerScore() {
        return dealerRevenue;
    }

    public Map<Name, Bet> getPlayerRevenue() {
        return Collections.unmodifiableMap(playerRevenue);
    }
}
