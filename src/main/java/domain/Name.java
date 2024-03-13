package domain;

import java.util.Objects;

public class Name {
    private static final String DEALER_NAME = "딜러";
    
    private final String name;

    public Name(final String name) {
        this.name = name;
    }

    public boolean isDealerName() {
        return name.equals(DEALER_NAME);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
