package model;

public class Agreement {
    private final boolean value;

    public Agreement(String value) {
        validate(value);
        this.value = value.equals("y");
    }

    public boolean get() {
        return value;
    }

    private void validate(String value) {
        validateEmptyValue(value);
        validatePossibleValue(value);
    }

    private void validateEmptyValue(String value) {
        if(value.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력하셨습니다.");
        }
    }

    private void validatePossibleValue(String value) {
        if(!value.equals("y") && !value.equals("n")) {
            throw new IllegalArgumentException("유효한 형식의 입력으로 넣어주세요(y 또는 n)");
        }
    }
}
