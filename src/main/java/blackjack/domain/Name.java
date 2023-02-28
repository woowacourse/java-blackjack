package blackjack.domain;

import java.util.Objects;

public class Name {

    private static final int MAX_LENGTH = 5;
    private static final String SPACE = " ";

    private final String name;

    public Name(String name) {
        validateSpace(name);
        validateLength(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 " + MAX_LENGTH + "자 이상일 수 없습니다.");
        }
    }

    private void validateSpace(String name) {
        if(name.isBlank() || name.contains(SPACE)) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
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
        return Objects.equals(getName(), name1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
