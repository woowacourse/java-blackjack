package blackjack.domain.participants;

import java.util.Objects;

import blackjack.exceptions.InvalidPlayerException;

public class Name {
    private final String name;

    public Name(final String input) {
        validate(input);
        this.name = input.trim();
    }

    private void validate(String input) {
        if (isNullOrEmpty(input)) {
            throw new InvalidPlayerException("빈 칸 또는 null 값이 들어올 수 없습니다.");
        }
    }

    private boolean isNullOrEmpty(final String name) {
        return Objects.isNull(name) || name.trim().isEmpty();
    }

    public String getName() {
        return name;
    }
}
