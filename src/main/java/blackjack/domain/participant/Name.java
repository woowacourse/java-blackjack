package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private final String name;

    public Name(String name) {
        validateNull(name);
        name = name.trim();
        validateEmpty(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateNull(final String name) {
        Objects.requireNonNull(name, "이름은 null일 수 없습니다.");
    }

    private void validateEmpty(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }
}
