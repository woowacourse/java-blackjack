package dto;

import domain.Money;
import domain.Name;
import domain.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingDto {

    private final Map<Name, Money> betting;

    public BettingDto() {
        betting = new HashMap<>();
    }

    public void putPlayerAndMoney(Player player, Money money) {
        betting.put(player.getName(), money);
    }

    public Map<Name, Money> getBetting() {
        return betting;
    }

}
