package blackjack.domain.player;

public class Name {
    private String value;

    public Name(String value) {
        validateContainBlank(value);
        this.value = value;
    }

    private void validateContainBlank(String value) {
        if (value.contains(" ")) {
            throw new IllegalArgumentException(String.format("%s 는 공백을 포함 하고 있습니다", value));
        }
    }

    public String getValue() {
        return value;
    }
}
