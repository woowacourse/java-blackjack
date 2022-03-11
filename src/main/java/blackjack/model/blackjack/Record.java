package blackjack.model.blackjack;

import blackjack.model.player.Name;
import java.util.Map;

public class Record {

    private Name name;
    private Map<Result, Long> record;

    public Record(Name name, Map<Result, Long> record) {
        this.name = name;
        this.record = Map.copyOf(record);
    }

    public Record(Name name, Result result) {
        this.name = name;
        this.record = Map.of(result, 1L);
    }

    public int countBy(Result result) {
        return record.getOrDefault(result, 0L)
            .intValue();
    }

    public Name name() {
        return name;
    }
}
