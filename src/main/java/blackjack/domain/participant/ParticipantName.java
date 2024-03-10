package blackjack.domain.participant;

import blackjack.exception.NeedRetryException;
import java.util.Objects;

public class ParticipantName {
    private final String name;

    public ParticipantName(final String input) {
        validateBlank(input);
        this.name = input.trim();
    }

    private void validateBlank(final String input) {
        if (input == null || input.isBlank()) {
            throw new NeedRetryException("참여자 이름에 공백을 입력할 수 없습니다.");
        }
    }

    public boolean is(final String otherName) {
        return name.equals(otherName);
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
        final ParticipantName that = (ParticipantName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
