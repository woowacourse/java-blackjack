package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerMoney {

    private final Map<Player, Money> playerMoney;

    public PlayerMoney() {
        this.playerMoney = new LinkedHashMap<>();
    }

    public PlayerMoney(Map<Player, Money> playerMoney) {
        this.playerMoney = playerMoney;
    }

    public void addPlayerMoney(Player player, Money money) {
        playerMoney.put(player, money);
    }

    public PlayerMoney calculateYieldAllPlayer(Map<Player, Result> playerResult) {
        Map<Player, Money> calculatedPlayerMoney = new LinkedHashMap<>();
        for (Player player : playerMoney.keySet()) {
            Money money = playerMoney.get(player);
            Result result = playerResult.get(player);
            calculatedPlayerMoney.put(player, result.calculateYield(money));
        }
        return new PlayerMoney(calculatedPlayerMoney);
    }

    public Money calculateDealerYield() {
        Money money = new Money(0);
        for (Player player : playerMoney.keySet()) {
            money = money.subtract(playerMoney.get(player));
        }
        return money;
    }

    public Map<Player, Money> getPlayerMoney() {
        return playerMoney;
    }
}
