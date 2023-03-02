package domain;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String target) {
        if (target.length() < 1 || target.length() > 5) {
            throw new IllegalArgumentException("[ERROR]");
        }
    }

    public String getValue() {
        return value;
    }

    public boolean isSameWith(String name) {
        return name.equals(value);
    }
}
