package domain.participant;

import java.util.Objects;

public class Name {
    private final String name;
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 5;

    public Name(String name) {
        validateBlank(name);
        validateLength(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void validateBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있거나 공백일 수 없습니다.");
        }
    }

    private static void validateLength(String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 2~5글자만 허용됩니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
