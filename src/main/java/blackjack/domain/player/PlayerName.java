package blackjack.domain.player;

import java.util.Objects;

public record PlayerName(String name) {

    public PlayerName {
        validateName(name);
    }

    private void validateName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름의 길이는 1이상입니다.");
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        PlayerName playerName1 = (PlayerName) object;
        return Objects.equals(name, playerName1.name);
    }
}
