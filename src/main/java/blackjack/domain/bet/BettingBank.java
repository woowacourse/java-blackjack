package blackjack.domain.bet;

import blackjack.domain.player.Player;
import java.util.Map;

public class BettingBank {

    private final Map<Player, Money> playerMoneyMap;

    public BettingBank(Map<Player, Money> playerMoneyMap) {
        this.playerMoneyMap = playerMoneyMap;
    }
}
