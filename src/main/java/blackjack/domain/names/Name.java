package blackjack.domain.names;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(String value) {
        validateName(value);
        this.value = value.trim();
    }

    private static void validateName(String name) {
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("공백은 이름으로 사용할 수 없습니다.");
        }
    }

    public String unwrap() {
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
        Name name1 = (Name) o;
        return Objects.equals(value, name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
