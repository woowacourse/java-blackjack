package domain.participant;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(String value) {
        value = value.strip();
        validateEmptyNames(value);
        this.value = value;
    }

    private static void validateEmptyNames(String playerName) {
        if (playerName.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 될 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
