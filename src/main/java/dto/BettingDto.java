package dto;

import domain.Money;
import domain.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingDto {

    private final Map<Player, Money> betting;

    public BettingDto() {
        betting = new HashMap<>();
    }

    public void putPlayerAndMoney(Player player, Money money) {
        betting.put(player, money);
    }

    public Map<Player, Money> getBetting() {
        return betting;
    }

}
