package domain.user;

public final class DealerName implements UserName {

    private final String name;

    public DealerName(String nameValue) {
        validate(nameValue);
        this.name = nameValue;
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
    public String getName() {
        return this.name;
    }
}
