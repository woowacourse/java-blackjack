package domain.player;

import java.util.Objects;

public class Name {

//    public static final Name DEALER_NAME = new Name("딜러");

    private final String name;

    public Name(final String name) {
        final String trimmed = name.trim();
        validate(trimmed);
        this.name = trimmed;
    }

    private void validate(final String name) {
        validateEmptiness(name);
        validateDealerName(name);
    }

    private void validateEmptiness(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }

    private void validateDealerName(final String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("이름은 딜러가 될 수 없습니다.");
        }
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
