package domain.participant;

public record PlayerName (String value) {
    public PlayerName {
        validateLength(value);
    }

    private void validateLength(String value) {
        if (value == null || value.isEmpty() || value.length() > 5) {
            throw new IllegalArgumentException("플레이어 이름은 1글자 이상 5글자 이하여야 합니다.");
        }
    }
}
