package domain.participant.name;

import java.util.Objects;

public class Name {
    private final String name;

    public Name(String name) {
        NameValidator.validateName(name);
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Name other) {
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
