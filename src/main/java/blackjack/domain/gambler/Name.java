package blackjack.domain.gambler;

import java.util.Objects;

public class Name {
    private final String name;

    public Name(final String name) {
        this.name = validateForm(name);
    }

    private String validateForm(final String name) {
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR]");
        }
        if (name.split(" ").length != 1) {
            throw new IllegalArgumentException("[ERROR]");
        }
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Name name1 = (Name) object;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
