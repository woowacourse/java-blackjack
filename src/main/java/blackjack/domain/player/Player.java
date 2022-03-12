package blackjack.domain.player;

import java.util.Objects;

public class Player {

    private final String name;

    public Player(final String name) {
        Objects.requireNonNull(name, "이름이 null이 들어올 수 없습니다.");
        validateBlankName(name);
        this.name = name;
    }

    private void validateBlankName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름이 공백이 들어올 수 없습니다.");
        }
    }
}
