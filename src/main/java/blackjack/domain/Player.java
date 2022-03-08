package blackjack.domain;

import java.util.Objects;

public class Player {

    private final String name;
    private boolean turnState;

    public Player(final String name, final boolean turnState) {
        this.turnState = turnState;
        Objects.requireNonNull(name, "[Error] 플레이어의 이름은 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[Error] 플레이어의 이름은 공백이 들어올 수 없습니다.");
        }
    }

    public boolean isEndPlayer() {
        return turnState;
    }
}
