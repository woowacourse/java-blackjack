package domain.participant;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(value, name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + value + '\'' +
                '}';
    }
}
