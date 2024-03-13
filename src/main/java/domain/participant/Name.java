package domain.participant;

import java.util.Objects;

public record Name(String value) {

    public Name {
        validateNotBlank(value);
    }

    private void validateNotBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름을 입력해주세요. 예) 포비, 호티, 제우스");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
