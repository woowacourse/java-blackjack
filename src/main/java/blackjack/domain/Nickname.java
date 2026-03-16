package blackjack.domain;

import java.util.Objects;

public class Nickname {

    private static final String DEALER_NICKNAME = "딜러";

    private final String value;

    private Nickname(String value) {
        this.value = value;
    }

    public static Nickname from(String playerName) {
        validate(playerName);
        return new Nickname(playerName);
    }

    public static Nickname makeDealerNickname() {
        return new Nickname(DEALER_NICKNAME);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
        if (DEALER_NICKNAME.equals(value)) {
            throw new IllegalArgumentException("딜러와 겹치는 닉네임을 입력할 수 없습니다. 다시 입력해주세요.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nickname nickname = (Nickname) o;
        return Objects.equals(value, nickname.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
