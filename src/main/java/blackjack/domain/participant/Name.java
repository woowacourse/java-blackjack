package blackjack.domain.participant;

public class Name {

    public static final String BLANK_NAME_EXCEPTION_MESSAGE = "이름은 빈 칸일 수 없습니다.";
    private final String value;

    public Name(String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION_MESSAGE);
        }
    }
}
