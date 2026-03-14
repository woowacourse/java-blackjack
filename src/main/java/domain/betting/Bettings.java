package domain.betting;

import domain.gamer.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Bettings {

    private final Map<PlayerName, BettingMoney> bettingMoneyByPlayer;

    public Bettings() {
        this.bettingMoneyByPlayer = new HashMap<>();
    }

    public void bet(PlayerName playerName, BettingMoney bettingMoney) {
        bettingMoneyByPlayer.put(playerName, bettingMoney);
    }

    public BettingMoney getPlayerBettingMoney(PlayerName playerName) {
        return bettingMoneyByPlayer.get(playerName);
    }

}
