package model.money;

import java.util.HashMap;
import java.util.Map;
import model.participant.Participant;

public class BettingTable {
    private final Map<Participant, Money> table;

    public BettingTable() {
        this.table = new HashMap<>();
    }

    public void add(Participant participant, Money money) {
        table.put(participant, money);
    }
}
