package domain.participant;

import java.util.Objects;

public class Name {

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (isNullOrBlank(name)) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름의 길이는 %s자를 초과할 수 없습니다.", MAX_NAME_LENGTH));
        }
    }

    private boolean isNullOrBlank(String name) {
        return Objects.isNull(name) || name.isBlank();
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Name otherName = (Name) other;
        return Objects.equals(name, otherName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
