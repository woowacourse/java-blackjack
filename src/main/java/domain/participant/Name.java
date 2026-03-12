package domain.participant;

public class Name {
    private static final String BLANK_NAME_NOT_ALLOWED = "[ERROR] 빈 값을 입력할 수 없습니다.";
    private static final String NAME_OUT_OF_RANGE = String.format("[ERROR] 이름은 %d ~ %d자 내여야 합니다.", 1, 10);
    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 10;
    private final String value;

    public Name(String name) {
        validate(name);
        this.value = name;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_NOT_ALLOWED);
        }
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(NAME_OUT_OF_RANGE);
        }
    }

    public String getValue() {
        return value;
    }
}
