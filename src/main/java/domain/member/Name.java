package domain.member;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    public boolean isName(String name) {
        return Objects.equals(this.value, name);
    }

    public String getValue() {
        return value;
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 값일 수 없습니다.");
        }
    }
}
