package domain.vo;

import java.util.Objects;

public class Name {
    private static final String ERROR_FOR_NULL_OR_BLANK_NAME = "[ERROR] 이름은 빈 값일 수 없습니다";

    private final String name;

    private Name(String name) {
        this.name = name;
        validateNullAndBlank(name);
    }

    private void validateNullAndBlank(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException(ERROR_FOR_NULL_OR_BLANK_NAME);
        }
    }

    public static Name from(String name) {
        return new Name(name);
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
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
