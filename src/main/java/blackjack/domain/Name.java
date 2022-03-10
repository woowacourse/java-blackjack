package blackjack.domain;

import java.util.Objects;

public class Name {

    private final String value;

    public Name(String value) {
        value = value.strip();
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        Objects.requireNonNull(value, "[ERROR] 이름은 null일 수 없습니다.");
        if (value.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 한 글자 이상이어야 합니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
