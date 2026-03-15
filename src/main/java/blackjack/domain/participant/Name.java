package blackjack.domain.participant;

import java.util.Objects;

public final class Name {
    private final String value;

    public Name(final String rawValue) {
        validate(rawValue);
        this.value = rawValue.strip();
    }

    private void validate(final String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("참가자 이름은 null 이거나 empty 일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Name name)) {
            return false;
        }
        return value.equals(name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public String getValue() {
        return value;
    }
}
