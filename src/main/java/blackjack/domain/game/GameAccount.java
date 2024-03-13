package blackjack.domain.game;

import blackjack.domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameAccount {

    private static final Map<Player, Money> store = new LinkedHashMap<>();

    public void betMoney(Player player, Money money) {
        store.put(player, money);
    }

    public Money findMoney(Player player) {
        return store.get(player);
    }
}
