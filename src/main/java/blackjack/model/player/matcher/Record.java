package blackjack.model.player.matcher;

import blackjack.model.player.Money;
import java.util.Objects;

public class Record {

    private final String name;
    private final Money profit;

    public Record(String name, Money profit) {
        this.name = name;
        this.profit = profit;
    }

    public String name() {
        return name;
    }

    public Money profit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return Objects.equals(name, record.name) && Objects
            .equals(this.profit, record.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profit);
    }
}
