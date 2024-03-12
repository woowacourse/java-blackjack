package blackjack.domain.betting;

import blackjack.domain.game.Result;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class Betting {

    private final Map<Player, Money> bettingMoney = new HashMap<>();

    public void bet(Player player, Money money) {
        bettingMoney.put(player, money);
    }

    public Money getPrize(Result result, Player player) {
        return result.getPrize(bettingMoney.get(player));
    }
}
