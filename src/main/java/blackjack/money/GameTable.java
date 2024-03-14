package blackjack.money;

import java.util.HashMap;
import java.util.Map;

public class GameTable {

    private final Map<String, Money> betMoney;

    public GameTable() {
        this.betMoney = new HashMap<>();
    }

    public void placeBet(String playerName, Money money) {
        betMoney.put(playerName, money);
    }
}
