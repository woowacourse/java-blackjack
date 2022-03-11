package blackjack.model.blackjack;

import java.util.Map;

public class Records {

    private Map<String, Record> records;

    public Records(Map<String, Record> records) {
        this.records = records;
    }

    public Record recordByName(String name) {
        return records.get(name);
    }
}
