package domain.betting;

import domain.betting.exception.BettingException;
import domain.betting.exception.ErrorMessage;
import domain.gamer.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Bettings {

    private final Map<PlayerName, BettingMoney> bettingMoneyByPlayer;

    public Bettings() {
        this.bettingMoneyByPlayer = new HashMap<>();
    }

    public void bet(PlayerName playerName, BettingMoney bettingMoney) {
        validateDuplicatedBetting(playerName);
        bettingMoneyByPlayer.put(playerName, bettingMoney);
    }

    public BettingMoney getPlayerBettingMoney(PlayerName playerName) {
        return bettingMoneyByPlayer.get(playerName);
    }

    private void validateDuplicatedBetting(PlayerName playerName) {
        if (bettingMoneyByPlayer.containsKey(playerName)) {
            throw new BettingException(ErrorMessage.DUPLICATED_PLAYER_BETTING);
        }
    }

}
