package domain;

import domain.user.Player;
import domain.user.Players;
import java.util.HashMap;
import java.util.Map;

public class BettingMoneyTable {
    private final Map<Player, BettingMoney> bettingMoneyTable;

    private BettingMoneyTable(Map<Player, BettingMoney> bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public static BettingMoneyTable of(Players players, BettingMonies bettingMonies) {
        Map<Player, BettingMoney> bettingMoneyTable = new HashMap<>();
        for (int i = 0; i < bettingMonies.size(); i++) {
            bettingMoneyTable.put(players.get(i), bettingMonies.get(i));
        }
        return new BettingMoneyTable(bettingMoneyTable);
    }

    public BettingMoney findByPlayer(Player player) {
        return bettingMoneyTable.get(player);
    }
}
