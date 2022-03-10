package blackjack.domain.participant;

public class Name {

    static final String EMPTY_NAME_EXCEPTION_MESSAGE = "[ERROR] 이름에 빈값을 입력할 수 없습니다.";

    private final String value;

    public Name(String value) {
        value = value.trim();
        validateName(value);
        this.value = value;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_EXCEPTION_MESSAGE);
        }
    }

    public String getValue() {
        return value;
    }
}
