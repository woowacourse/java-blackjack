package blackjack.domain.result;

import blackjack.domain.bet.Profit;
import blackjack.domain.player.Player;
import java.util.Map;

public class PlayerProfitResult {

    private final Map<Player, Profit> playerProfitMap;

    public PlayerProfitResult(Map<Player, Profit> playerProfitMap) {
        this.playerProfitMap = playerProfitMap;
    }
}
