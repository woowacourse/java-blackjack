package domain.gamer;

import java.util.Objects;

public class GamerName {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 20;

    private final String value;

    public GamerName(String value) {
        validateLength(value);
        this.value = value;
    }

    private void validateLength(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "[ERROR] 이름의 길이는 " + MIN_LENGTH + " ~ " + MAX_LENGTH + " 글자 사이로 입력해주세요.");
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
        GamerName gamerName = (GamerName) o;
        return Objects.equals(value, gamerName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
