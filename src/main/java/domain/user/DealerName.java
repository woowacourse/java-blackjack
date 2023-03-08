package domain.user;

public class DealerName implements UserName {

    private final String value;

    public DealerName(String nameValue) {
        validate(nameValue);
        this.value = nameValue;
    }

    private void validate(String nameValue) {
        validateNullAndBlank(nameValue);
    }

    private static void validateNullAndBlank(String nameValue) {
        if (nameValue == null || nameValue.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이나 빈 값이 들어갈 수 없습니다.");
        }
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
