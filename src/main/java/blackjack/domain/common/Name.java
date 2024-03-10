package blackjack.domain.common;

public class Name {
    private final String value;

    public Name(final String value) {
        validateContainBlank(value);
        this.value = value;
    }

    public String asString() {
        return value;
    }

    private void validateContainBlank(final String value) {
        if (value.contains(" ")) {
            throw new IllegalArgumentException(String.format("%s 는 공백을 포함 하고 있습니다", value));
        }
    }
}
