package domain;

import java.util.Map;

public class BettingTable {
    private final Map<Player, Money> moneyTable;

    public BettingTable(Map<Player, Money> moneyTable){
        this.moneyTable = moneyTable;
    }
}
