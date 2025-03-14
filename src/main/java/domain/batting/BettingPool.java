package domain.batting;

import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class BettingPool {
    private final Map<Player, Bet> bets;

    private BettingPool(Map<Player, Bet> bets) {
        this.bets = bets;
    }

    public static BettingPool of() {
        return new BettingPool(new HashMap<>());
    }

    public boolean wager(Player player, Bet bet) {
        if (bets.containsKey(player)) {
            return false;
        }
        bets.put(player, bet);
        return true;
    }

    public Bet getPlayerBet(Player player) {
        try {
            return bets.get(player);
        } catch (ClassCastException | NullPointerException exception) {
            throw new IllegalStateException();
        }
    }
}
