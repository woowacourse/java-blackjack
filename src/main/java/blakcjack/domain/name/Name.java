package blakcjack.domain.name;

import java.util.Objects;

public class Name {
    public static final String NULL_NAME_ERROR = "플레이어 이름에 null이 입력되었습니다.";
    public static final String EMPTY_NAME_ERROR = "빈 문자열이 입력되었습니다.";

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (Objects.isNull(name)) {
            throw new IllegalPlayerNameException(NULL_NAME_ERROR);
        }
        if (name.isEmpty()) {
            throw new IllegalPlayerNameException(EMPTY_NAME_ERROR);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
