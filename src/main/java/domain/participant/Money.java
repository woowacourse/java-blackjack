package domain.participant;

public class Money {

    private final int value;

    public Money(String rawValue) {
        validateNullOrBlank(rawValue);
        validateIntegerFormat(rawValue);
        if (Integer.parseInt(rawValue) <= 0) {
            throw new IllegalArgumentException("입력은 0보다 커야 합니다.");
        }
        this.value = Integer.parseInt(rawValue);
    }

    private void validateNullOrBlank(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("입력이 공백이거나 null 입니다.");
        }
    }

    private void validateIntegerFormat(String rawValue) {
        try {
            Integer.parseInt(rawValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력이 정수 형식이 아닙니다.");
        }
    }
}
