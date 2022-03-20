package blackjack.model.player;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Records {

    private List<Record> records;

    public Records(List<Record> records) {
        this.records = List.copyOf(records);
    }

    public Collection<Record> values() {
        return Collections.unmodifiableList(records);
    }

    public Money dealerProfit() {
        return records.stream()
            .map(Record::profit)
            .map(Money::negate)
            .reduce(new Money(BigDecimal.ZERO), Money::add);
    }
}
