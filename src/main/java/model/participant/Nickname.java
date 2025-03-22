package model.participant;

import java.util.Objects;

public class Nickname {

    private static final String NICKNAME_PATTERN = "^[a-z]{2,10}$";

    private final String value;

    public Nickname(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (!value.matches(NICKNAME_PATTERN)) {
            throw new IllegalArgumentException("닉네임은 2~10자만 입력 가능합니다,");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nickname nickname = (Nickname) o;
        return Objects.equals(value, nickname.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
