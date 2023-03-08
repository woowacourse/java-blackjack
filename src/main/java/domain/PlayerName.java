package domain;

public class PlayerName {

    private final String value;

    public PlayerName(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("입력되지 않은 이름이 있습니다. 다시 입력해주세요.");
        }
    }

    public String getValue() {
        return value;
    }
}
