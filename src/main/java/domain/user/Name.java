package domain.user;

public class Name {
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;
    private static final String NAME_LENGTH_EXCEPTION_MESSAGE = "[ERROR] 이름의 길이는 1~5자 이어야 합니다.";

    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String target) {
        if (target.length() < MIN_NAME_LENGTH || target.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    public String getValue() {
        return value;
    }
}
