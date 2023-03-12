package blackjack.domain.participant;

import java.util.Objects;

public class Name {

    private static final int MAX_LENGTH = 5;
    private static final String SPACE = " ";
    private static final String BAN_NAME = "딜러";

    private final String name;

    public Name(String name) {
        validateSpace(name);
        validateLength(name);
        validateIsNameDealer(name);
        this.name = name;
    }

    private void validateIsNameDealer(String name) {
        if (BAN_NAME.equals(name)) {
            throw new IllegalArgumentException("이름은 딜러가 될 수 없습니다.");
        }
    }

    private void validateSpace(String name) {
        if (name.isBlank() || name.contains(SPACE)) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    private void validateLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 " + MAX_LENGTH + "자 이상일 수 없습니다.");
        }
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(getValue(), name1.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
