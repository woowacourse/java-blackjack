package domain.participant;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private void validate(final String name) {
        validateNull(name);
        validateBlank(name);
    }

    private void validateNull(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 참여자 이름입니다.");
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 참여자 이름에 공백을 입력할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        }

        if (!(target instanceof Name name)) {
            return false;
        }

        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
