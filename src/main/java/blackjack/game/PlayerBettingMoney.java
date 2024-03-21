package blackjack.game;

import blackjack.player.Player;
import java.util.Map;

public class PlayerBettingMoney {

    private final Map<Player, Money> bettingMoney;

    public PlayerBettingMoney(Map<Player, Money> bettingMoney) {
        this.bettingMoney = Map.copyOf(bettingMoney);
    }

    public Money getBettingAmountOf(Player player) {
        if (!bettingMoney.containsKey(player)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
        }
        return bettingMoney.get(player);
    }

    public Map<Player, Money> getBettingMoney() {
        return bettingMoney;
    }
}
