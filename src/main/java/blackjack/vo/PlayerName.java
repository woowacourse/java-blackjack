package blackjack.vo;

public record PlayerName(String name) {
    private static final String INVALID_NAME_LENGTH = "플레이어 이름은 한 글자 이상이어야 합니다.";

    public PlayerName {
        validateName(name);
    }

    private void validateName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
