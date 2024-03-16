package domain.participant;

public record PlayerName (String value) {
    private static final int MAX_NAME_LENGTH = 5;

    public PlayerName {
        validateLength(value);
    }

    private void validateLength(final String value) {
        if (value == null || value.isEmpty() || value.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(
                    "%s는 올바른 이름이 아닙니다. 플레이어 이름은 1글자 이상 %d글자 이하여야 합니다.", value, MAX_NAME_LENGTH));
        }
    }
}
