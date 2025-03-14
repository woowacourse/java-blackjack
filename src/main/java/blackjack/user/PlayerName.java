package blackjack.user;

import java.util.Objects;

public class PlayerName {

    private final String name;

    public PlayerName(final String name) {
        validateNameSize(name);
        validateCharacter(name);

        this.name = name;
    }

    private void validateNameSize(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 한 글자 이상 입력해야 합니다.");
        }
    }

    private void validateCharacter(final String name) {
        boolean isNotAllLetter = name.chars().anyMatch(c -> !Character.isLetter(c));

        if (isNotAllLetter) {
            throw new IllegalArgumentException("이름은 영어/한글만 입력 가능합니다.");
        }
    }

    public String getText() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PlayerName that = (PlayerName) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
