package blackjack.domain;

public class Name {

    private final String value;

    public Name(String value) {
        this.value = value;
        validate(this.value);
    }

    private void validate(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 아니어야합니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
