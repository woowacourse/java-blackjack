package blackjack.model.participant;

import java.util.Objects;

public class Name {
    private final String rawName;

    public Name(final String name) {
        validateNullAndEmptyName(name);
        this.rawName = name;
    }

    private void validateNullAndEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백을 사용할 수 없습니다.");
        }
    }

    public String getRawName() {
        return rawName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(rawName, name.rawName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawName);
    }
}
