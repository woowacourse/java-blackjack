package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private final String name;

    public Name(String name) {
        validateNameSize(name);

        this.name = name;
    }

    private void validateNameSize(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("사용자의 이름은 공백을 제외한 1글자 이상입니다.");
        }
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name other = (Name) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
