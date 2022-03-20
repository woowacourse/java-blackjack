package blackjack.model.player;

import blackjack.model.player.matcher.Money;
import blackjack.model.player.matcher.Record;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class Records {

    private List<Record> records;

    public Records(List<Record> records) {
        this.records = List.copyOf(records);
    }

    public Collection<Record> values() {
        return records;
    }

    public Money dealerProfit() {
        return records.stream()
            .map(Record::profit)
            .map(Money::negate)
            .reduce(new Money(BigDecimal.ZERO), Money::add);
    }
}
