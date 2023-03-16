package blackjack.domain.betting;

import blackjack.domain.participant.Participant;

import java.util.Map;

public class BettingTable {

    private final Map<Participant, Betting> table;

    private BettingTable(Map<Participant, Betting> table) {
        this.table = table;
    }

    public static BettingTable from(Map<Participant, Betting> table) {
        return new BettingTable(table);
    }

    public void addBetting(Participant participant, Betting betting) {
        table.put(participant, betting);
    }

    public Map<Participant, Betting> getTable() {
        return table;
    }
}
