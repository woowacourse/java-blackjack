package blackjack.model.blackjack;

import java.util.Objects;

public class Record {

    private final String name;
    private final Result result;

    public Record(String name, Result result) {
        this.name = name;
        this.result = result;
    }

    public String name() {
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
