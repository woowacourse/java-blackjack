package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(String value) {
        validateNameSize(value);

        this.value = value;
    }

    private void validateNameSize(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("사용자의 이름은 공백을 제외한 1글자 이상입니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
