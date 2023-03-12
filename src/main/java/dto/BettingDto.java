package dto;

import domain.Amount;
import domain.Name;
import domain.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingDto {

    private final Map<Name, Amount> betting;

    public BettingDto() {
        betting = new LinkedHashMap<>();
    }

    public void putPlayerAndAmount(Player player, Amount amount) {
        betting.put(player.getName(), amount);
    }

    public Map<Name, Amount> getBetting() {
        return betting;
    }

}
