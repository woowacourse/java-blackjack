package dto;

import domain.Money;
import domain.Name;
import domain.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingDto {

    private final Map<Name, Money> betting;

    public BettingDto() {
        betting = new LinkedHashMap<>();
    }

    public void putPlayerAndMoney(Player player, Money money) {
        betting.put(player.getName(), money);
    }

    public Map<Name, Money> getBetting() {
        return betting;
    }

}
