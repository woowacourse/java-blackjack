package domain.participant;

public record PlayerName (String value) {
    private static final int MAXIMUM_ENABLE_NAME_LENGTH = 5;
    private static final String DENIED_NAME = "딜러";

    public PlayerName {
        validateLength(value);
        validateDeniedName(value);
    }

    private void validateLength(final String value) {
        if (value == null || value.isEmpty() || value.length() > MAXIMUM_ENABLE_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 1글자 이상 5글자 이하여야 합니다.");
        }
    }

    private void validateDeniedName(final String value) {
        if (value.equals(DENIED_NAME)) {
            throw new IllegalArgumentException("설정할 수 없는 플레이어 이름 입니다.");
        }
    }
}
