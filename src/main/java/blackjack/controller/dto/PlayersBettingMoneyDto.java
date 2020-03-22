package blackjack.controller.dto;

import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.Map;

public class PlayersBettingMoneyDto {

    private Map<Player, String> bettingTable;

    public PlayersBettingMoneyDto(Map<Player, String> bettingTable) {
        validate(bettingTable);
        this.bettingTable = Collections.unmodifiableMap(bettingTable);
    }

    private void validate(Map<Player, String> bettingTable) {
        if (bettingTable == null || bettingTable.isEmpty()) {
            throw new IllegalArgumentException("사용자의 배팅 금액을 입력해주세요.");
        }
    }

    public Map<Player, String> get() {
        return bettingTable;
    }
}
