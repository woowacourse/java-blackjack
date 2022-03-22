package domain.participant;

import java.util.Objects;

public class Name {

    private static final String BLANK_NAME_ERROR_MESSAGE_FORMAT = "[Error] \"%s\" : 이름은 공백이거나 빈칸일 수 없습니다.";

    private final String value;

    public Name(String value) {
        validateName(value);
        this.value = value;
    }

    protected void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format(BLANK_NAME_ERROR_MESSAGE_FORMAT, name));
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        Name name1 = (Name) o;
        return value.equals(name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
