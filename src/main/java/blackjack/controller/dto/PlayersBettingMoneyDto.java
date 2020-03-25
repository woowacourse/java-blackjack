package blackjack.controller.dto;

import java.util.Collections;
import java.util.Map;

public class PlayersBettingMoneyDto {

    private Map<String, String> playersBettingMoney;

    public PlayersBettingMoneyDto(Map<String, String> playersBettingMoney) {
        validate(playersBettingMoney);
        this.playersBettingMoney = Collections.unmodifiableMap(playersBettingMoney);
    }

    private void validate(Map<String, String> playersBettingMoney) {
        if (playersBettingMoney == null || playersBettingMoney.isEmpty()) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
    }

    public Map<String, String> get() {
        return playersBettingMoney;
    }
}
