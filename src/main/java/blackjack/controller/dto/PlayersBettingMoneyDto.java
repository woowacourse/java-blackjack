package blackjack.controller.dto;

import java.util.Map;

public class PlayersBettingMoneyDto {

    private Map<String, String> bettingTable;

    public PlayersBettingMoneyDto(Map<String, String> bettingTable) {
        validate(bettingTable);
        this.bettingTable = bettingTable;
    }

    private void validate(Map<String, String> bettingTable) {
        if (bettingTable == null || bettingTable.isEmpty()) {
            throw new IllegalArgumentException("사용자의 배팅 금액을 입력해주세요.");
        }
    }

    public Map<String, String> get() {
        return bettingTable;
    }
}
