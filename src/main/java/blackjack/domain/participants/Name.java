package blackjack.domain.participants;

public class Name {
    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 한 글자 이상이어야 합니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
