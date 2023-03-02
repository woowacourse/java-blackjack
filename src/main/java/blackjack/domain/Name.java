package blackjack.domain;

public class Name {

    static final String BLANK_NAME_EXCEPTION_MESSAGE = "이름은 공백일 수 없습니다.";

    private final String value;

    public Name(String name) {
        validateBlank(name);
        this.value = name;
    }

    private void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION_MESSAGE);
        }
    }

    public String getValue() {
        return value;
    }
}
