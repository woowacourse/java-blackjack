package blackjack.domain.participant;

import java.util.Objects;

public final class Name {
    private final String cleaned;

    public Name(final String rawValue) {
        validate(rawValue);
        this.cleaned = rawValue.strip();
    }

    private void validate(final String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("참가자 이름은 null 이거나 empty 일 수 없습니다.");
        }
    }

    public String getCleaned() {
        return cleaned;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Name name)) {
            return false;
        }
        return cleaned.equals(name.cleaned);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cleaned);
    }
}
