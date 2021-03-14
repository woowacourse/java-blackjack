package blackjack.domain.participant;

import blackjack.exception.InvalidNameInputException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final Pattern VALID_NAME_FORMAT = Pattern.compile("^[0-9가-힣a-zA-Z\\s]*$");
    private final String value;

    public Name(String name) throws InvalidNameInputException {
        validateName(name);
        this.value = name;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || !VALID_NAME_FORMAT.matcher(name).matches()) {
            throw new InvalidNameInputException("이름은 한글, 영문 혹은 숫자로 1자 이상 입력해야합니다.");
        }
    }

    public String value() {
        return this.value;
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
