package blackjack.domain.participant;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(String value) {
        String valueWithoutBlank = removeBlank(value);
        validateNameLength(valueWithoutBlank);
        this.value = valueWithoutBlank;
    }

    private static String removeBlank(String value) {
        return value.replace(" ", "");
    }

    private static void validateNameLength(String value) {
        if (value.length() < 1 || value.length() > 5) {
            throw new IllegalArgumentException("이름은 1글자 이상 5글자 이하만 가능합니다.");
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
