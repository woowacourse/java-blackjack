package blackjack.model.player;

import java.util.Objects;

public class PlayerName {
    public static final int MINIMUM_NAME_LENGTH = 2;
    public static final int MAXIMUM_NAME_LENGTH = 5;

    private final String name;

    public PlayerName(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자 이름을 입력해주세요.");
        }
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("참가자 이름은 %d~%d글자 입니다.", MINIMUM_NAME_LENGTH, MAXIMUM_NAME_LENGTH));
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
