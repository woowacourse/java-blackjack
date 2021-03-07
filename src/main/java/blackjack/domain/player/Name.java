package blackjack.domain.player;

import blackjack.exception.NameException;

import java.util.Objects;

public class Name {

    private final String name;

    public Name(String name) {
        name = name.trim();
        validateNull(name);
        validateEmpty(name);
        this.name = name;
    }

    public String value() {
        return name;
    }

    private void validateNull(final String name) {
        Objects.requireNonNull(name, "이름은 null일 수 없습니다.");
    }

    private void validateEmpty(final String name) {
        if (name.isEmpty()) {
            throw new NameException("이름은 비어있을 수 없습니다.");
        }
    }
}
