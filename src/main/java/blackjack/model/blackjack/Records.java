package blackjack.model.blackjack;

import blackjack.model.player.Name;
import java.util.Map;

public class Records {

    private Map<Name, Record> records;

    public Records(Map<Name, Record> records) {
        this.records = records;
    }

    public Record recordByName(Name name) {
        return records.get(name);
    }
}
