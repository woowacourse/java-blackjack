package blackjack.domain.participant;

import blackjack.exception.InvalidInputException;

public class Name {

    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;

    private final String value;

    public Name(String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < name.length()) {
            throw new InvalidInputException("이름은 1~5자만 가능합니다");
        }
    }

    public String getName() {
        return value;
    }
}
