package blackjack.domain.paticipant;

import java.util.Objects;

public class Player {

    private final String name;

    public Player(final String name) {
        Objects.requireNonNull(name, "이름은 null이 들어올 수 없습니다.");
        this.name = name;
    }
}
