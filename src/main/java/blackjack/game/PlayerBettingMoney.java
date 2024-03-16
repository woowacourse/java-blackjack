package blackjack.game;

import blackjack.player.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerBettingMoney {

    private final Map<Player, Money> bettingMoney;

    public PlayerBettingMoney() {
        this.bettingMoney = new HashMap<>();
    }

    public void addBetting(Player player, Money money) {
        if (bettingMoney.containsKey(player)) {
            throw new IllegalArgumentException("[ERROR] 이미 존재하는 플레이어입니다.");
        }
        bettingMoney.put(player, money);
    }

    public Money getBettingAmountOf(Player player) {
        if (!bettingMoney.containsKey(player)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 플레이어입니다.");
        }
        return bettingMoney.get(player);
    }
}
