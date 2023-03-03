package blackjack.domain;

public class Name {

    private final String value;

    public Name(String value) {
        validateName(value);

        this.value = value;
    }

    private void validateName(String value) {
        if (value == null) {
            throw new IllegalArgumentException("이름이 입력되지 않았습니다.");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
