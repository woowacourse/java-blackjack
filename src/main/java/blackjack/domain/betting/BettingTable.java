package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import java.util.Map;

public class BettingTable {

    private final Map<Player, Betting> expectedProfit;

    public BettingTable(final Map<Player, Betting> expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    private void validateExistPlayer(final Player player) {
        if (!expectedProfit.containsKey(player)) {
            throw new IllegalArgumentException("베팅하지 않은 플레이어입니다.");
        }
    }
}
