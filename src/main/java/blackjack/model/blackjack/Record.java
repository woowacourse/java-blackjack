package blackjack.model.blackjack;

import blackjack.model.player.Name;
import java.util.Objects;

public class Record {

    private final Name name;
    private final Result result;

    public Record(Name name, Result result) {
        this.name = name;
        this.result = result;
    }

    public Name name() {
        return name;
    }

    public Result result() {
        return result;
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
        return Objects.equals(name, record.name) && result == record.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, result);
    }
}
