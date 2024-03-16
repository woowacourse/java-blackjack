package domain;

public class Money {
    private final int value;

    public Money(String value) {
        try {
            this.value = Integer.parseInt(value);
        } catch (NumberFormatException ne) {
            throw new NumberFormatException("양의 정수만 입력하세요.");
        }
        validate();
    }

    private void validate() {
        if (value <= 0) {
            throw new IllegalArgumentException("양의 정수만 입력하세요.");
        }
    }

}
