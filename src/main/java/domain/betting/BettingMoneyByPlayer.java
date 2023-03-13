package domain.betting;

import java.util.HashMap;
import java.util.Map;

import domain.participant.Player;

public class BettingMoneyByPlayer {

    private final Map<Player, BettingMoney> bettingMoneyByPlayer;

    public BettingMoneyByPlayer() {
        this.bettingMoneyByPlayer = new HashMap<>();
    }

    public void putPlayerBettingMoney(Player player, BettingMoney bettingMoney) {
        bettingMoneyByPlayer.put(player, bettingMoney);
    }

    public BettingMoney findBettingMoneyByPlayer(Player player) {
        return bettingMoneyByPlayer.get(player);
    }
}
