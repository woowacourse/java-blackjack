package blackjack.controller.dto.request;

import java.util.Map;

public class BettingDto {

    private Map<String, String> bettingTable;

    public BettingDto(Map<String, String> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public Map<String, String> getBettingTable() {
        return bettingTable;
    }
}
