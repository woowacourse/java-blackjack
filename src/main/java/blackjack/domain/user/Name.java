package blackjack.domain.user;

import java.util.Objects;

public class Name {

    private static final String NAME_LENGTH_ERROR = "[ERROR] 이름은 한글자 이상 입력해 주세요.";

    private final String name;

    public Name(String input) {
        validate(input.trim());
        this.name = input;
    }

    private void validate(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR);
        }
    }

    public String getName() {
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
        Name that = (Name) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
