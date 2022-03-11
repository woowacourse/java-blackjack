package blackjack.model.blackjack;

import java.util.Map;

public class Record {

    private String name;
    private Map<Result, Long> record;

    public Record(String name, Map<Result, Long> record) {
        this.name = name;
        this.record = Map.copyOf(record);
    }

    public Record(String name, Result result) {
        this.name = name;
        this.record = Map.of(result, 1L);
    }

    public int countBy(Result result) {
        return record.getOrDefault(result, 0L)
            .intValue();
    }

    public String name() {
        return name;
    }
}
