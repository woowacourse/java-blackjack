package blackjack.participant;

import java.util.Objects;

public class Nickname {

    private final String value;

    private Nickname(String value) {
        validateBlank(value);
        this.value = value;
    }

    public static Nickname from(String value) {
        return new Nickname(value);
    }

    private void validateBlank(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("닉네임은 공백을 허용하지 않습니다.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nickname nickname = (Nickname) o;
        return Objects.equals(value, nickname.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
