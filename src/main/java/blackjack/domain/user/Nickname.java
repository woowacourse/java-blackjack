package blackjack.domain.user;

import blackjack.exception.ExceptionMessage;
import java.util.Objects;

public final class Nickname {

    private final String value;

    public Nickname(String value) {
        validateBlank(value);
        this.value = value;
    }

    private void validateBlank(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_ALLOWED_EMPTY_NICKNAME.getContent());
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Nickname nickname = (Nickname) object;
        return Objects.equals(value, nickname.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
