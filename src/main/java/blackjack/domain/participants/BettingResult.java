package blackjack.domain.participants;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {
    private final Map<Player, Profit> bettingResult;

    public BettingResult() {
        this.bettingResult = new LinkedHashMap<>();
    }

    public void bet(Player player, Profit profit) {
        bettingResult.put(player, profit);
    }

    public Profit calculateProfit(Player player, State state) {
        if (!bettingResult.containsKey(player)) {
            throw new IllegalArgumentException("해당 유저는 베팅하지 않았습니다.");
        }
        if (state == State.WIN && player.isBlackjack()) {
            return bettingResult.get(player).multiple(1.5);
        }
        if (state == State.WIN) {
            return bettingResult.get(player);
        }
        if (state == State.LOSE) {
            return bettingResult.get(player).inverse();
        }
        return new Profit(0);
    }
}
