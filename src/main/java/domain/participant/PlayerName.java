package domain.participant;

public record PlayerName (String value) {
    private static final int MAXIMUM_ENABLE_NAME_LENGTH = 5;

    public PlayerName {
        validateLength(value);
    }

    private void validateLength(final String value) {
        if (value == null || value.isEmpty() || value.length() > MAXIMUM_ENABLE_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 1글자 이상 5글자 이하여야 합니다.");
        }
    }
}
