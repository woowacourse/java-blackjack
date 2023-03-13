package blackjack.domain.bet;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.participant.Player;

public class BetManager {

    private final Map<Player, Money> betManager = new HashMap<>();

    public void add(Player player, Money betMoney) {
        betManager.put(player, betMoney);
    }

    public Money getBetMoney(Player player) {
        return betManager.get(player);
    }
}
