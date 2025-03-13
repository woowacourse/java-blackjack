package domain.participant;

public class Money {

    private final int value;

    public Money(String rawValue) {
        validateNullOrBlank(rawValue);
        this.value = Integer.parseInt(rawValue);
    }

    private void validateNullOrBlank(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new IllegalArgumentException("입력이 공백이거나 null 입니다.");
        }
    }
}
