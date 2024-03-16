package blackjack.domain.user;

import java.util.Objects;

public final class UserName {

    private final String name;

    public UserName(String input) {
        validateBlank(input);
        this.name = input.trim();
    }

    private void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("참여자 이름에 공백을 입력할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserName userName)) {
            return false;
        }
        return Objects.equals(getName(), userName.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
