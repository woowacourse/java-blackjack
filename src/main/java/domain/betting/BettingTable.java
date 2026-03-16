package domain.betting;

import java.util.ArrayList;
import java.util.List;

public class BettingTable {
    public final List<PlayerBetting> bettingTable;

    private BettingTable(List<PlayerBetting> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public static BettingTable create() {
        List<PlayerBetting> bettingTable = new ArrayList<>();
        return new BettingTable(bettingTable);
    }

    public void add(PlayerBetting playerBetting) {
        bettingTable.add(playerBetting);
    }
}
