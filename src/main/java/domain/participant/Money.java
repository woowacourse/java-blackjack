package domain.participant;

public class Money {

    private final int value;

    public Money(String rawValue) {
        validateBlank(rawValue);
        this.value = Integer.parseInt(rawValue);
    }

    private void validateBlank(String rawValue) {
        if (rawValue.isBlank()) {
            throw new IllegalArgumentException("입력이 공백이거나 null 입니다.");
        }
    }
}
