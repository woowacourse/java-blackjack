package domain.participant;

import java.util.Objects;

public class Name {
    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 10;
    private static final String BLANK_NAME_NOT_ALLOWED = "[ERROR] 빈 값을 입력할 수 없습니다.";
    private static final String NAME_OUT_OF_RANGE = String.format("[ERROR] 이름은 %d ~ %d자 내여야 합니다.", NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    private final String value;

    private Name(String name) {
        this.value = name;
    }

    public static Name valueOf(String name) {
        validate(name);
        return new Name(name);
    }

    private static void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_NOT_ALLOWED);
        }
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_OUT_OF_RANGE);
        }
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

    public String getValue() {
        return value;
    }
}
