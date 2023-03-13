package domain.money;

import domain.user.Player;
import domain.user.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingMoneyTable {
    private final Map<Player, Money> bettingMoneyTable;

    private BettingMoneyTable(Map<Player, Money> bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public static BettingMoneyTable of(List<Player> players, BettingMonies bettingMonies) {
        Map<Player, Money> bettingMoneyTable = new HashMap<>();
        for (int i = 0; i < bettingMonies.size(); i++) {
            bettingMoneyTable.put(players.get(i), bettingMonies.get(i));
        }
        return new BettingMoneyTable(bettingMoneyTable);
    }

    public static BettingMoneyTable of(Players players, BettingMonies bettingMonies) {
        Map<Player, Money> bettingMoneyTable = new HashMap<>();
        for (int i = 0; i < bettingMonies.size(); i++) {
            bettingMoneyTable.put(players.get(i), bettingMonies.get(i));
        }
        return new BettingMoneyTable(bettingMoneyTable);
    }

    public Money findByPlayer(Player player) {
        return bettingMoneyTable.get(player);
    }
}
