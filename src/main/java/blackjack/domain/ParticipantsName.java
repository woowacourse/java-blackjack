package blackjack.domain;

import java.util.Objects;

public class ParticipantsName {
    private final String name;

    public ParticipantsName(final String input) {
        validateBlank(input);
        this.name = input;
    }

    private void validateBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("참여자 이름에 공백을 입력할 수 없습니다.");
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ParticipantsName that = (ParticipantsName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
