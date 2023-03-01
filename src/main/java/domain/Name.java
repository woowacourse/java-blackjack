package domain;

import java.util.Objects;

public class Name {

    private final String name;

    public Name(final String name) {
        if (Objects.isNull(name) || name.isEmpty() || name.length() > 5) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
