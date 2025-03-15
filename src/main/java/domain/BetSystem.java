package domain;

import java.util.HashMap;
import java.util.Map;

public class BetSystem {

    private final Map<Gamer, Integer> betRecord = new HashMap<>();

    public void betting(final Player player, final int betAmount) {
        validateBetAmount(betAmount);

        betRecord.put(player, betAmount);
    }

    private void validateBetAmount(final int betAmount) {
        if (betAmount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 이상이어야 합니다.");
        }
    }
}
