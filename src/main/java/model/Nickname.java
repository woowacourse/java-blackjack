package model;

import java.util.Objects;

public class Nickname {

    private final String value;

    public Nickname(final String value) {
        validateLength(value);
        this.value = value;
    }

    private static void validateLength(String value) {
        if( value.length() < 2 || value.length() > 10) {
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
