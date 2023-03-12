package blackjack.domain.game;

import java.util.Map;

public class BettingTable {

    private final Map<String, Betting> bettingTable;

    public BettingTable(Map<String, Betting> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public Betting getBetting(String name) {
        if (!bettingTable.containsKey(name)) {
            throw new IllegalArgumentException("해당 이름과 일치하는 배팅이 없습니다.");
        }
        return bettingTable.get(name);
    }
}
