package blackjack.domain;

import java.util.Objects;

public final class Nickname {

    private static final String DEALER_NICKNAME = "딜러";

    private final String value;

    public Nickname(String value) {
        validateBlank(value);
        this.value = value;
    }

    public static Nickname createDealer() {
        return new Nickname(DEALER_NICKNAME);
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Nickname nickname = (Nickname) object;
        return Objects.equals(value, nickname.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
