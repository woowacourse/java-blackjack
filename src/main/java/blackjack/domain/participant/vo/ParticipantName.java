package blackjack.domain.participant.vo;

import java.util.Objects;

public class ParticipantName {
    private final String value;

    public ParticipantName(final String value) {
        Objects.requireNonNull(value, "이름에는 null이 들어올 수 없습니다.");
        validateEmptyName(value);
        this.value = value;
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이 들어올 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ParticipantName that = (ParticipantName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
