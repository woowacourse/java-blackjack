package domain.participant;

import java.util.Objects;

public class Name {

    private final String text;

    public Name(String text) {
        validate(text);
        this.text = text;
    }

    private void validate(String name) {
        validateNotNull(name);
    }

    private void validateNotNull(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 텍스트를 가져야 합니다.");
        }
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Name name = (Name) o;

        return Objects.equals(text, name.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(text);
    }
}
