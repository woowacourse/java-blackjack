package blackjack.domain.participant;

import java.util.Objects;

public class Nickname {
    private static final String WRONG_PLAYER_NAME_ERROR_MESSAGE = "유효하지 않은 플레이어 이름입니다.";

    private final String name;

    public Nickname(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException(WRONG_PLAYER_NAME_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nickname nickname1 = (Nickname) o;
        return Objects.equals(name, nickname1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
