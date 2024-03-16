package blackjack.domain.player;

import blackjack.exception.NeedRetryException;
import java.util.Objects;

public final class UserName {

    private final String name;

    public UserName(final String input) {
        validateBlank(input);
        this.name = input.trim();
    }

    private void validateBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new NeedRetryException("참여자 이름에 공백을 입력할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final UserName userName)) {
            return false;
        }
        return Objects.equals(getName(), userName.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
