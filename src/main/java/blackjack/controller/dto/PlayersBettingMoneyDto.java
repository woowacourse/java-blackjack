package blackjack.controller.dto;

import java.util.Map;

public class PlayersBettingMoneyDto {

    private Map<String, String> bettingTable;

    public PlayersBettingMoneyDto(Map<String, String> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public Map<String, String> get() {
        return bettingTable;
    }
}
